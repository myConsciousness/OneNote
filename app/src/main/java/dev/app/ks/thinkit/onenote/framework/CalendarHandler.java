package dev.app.ks.thinkit.onenote.framework;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * ======================================================================
 * Project Name    : Common
 * File Name       : CalendarHandler.java
 * Encoding        : UTF-8
 * Creation Date   : 2019/10/19
 * <p>
 * Copyright © 2019 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be
 * reproduced or used in any manner whatsoever.
 * ======================================================================
 * <p>
 * 日付と日時を操作する汎用的な機能を定義したユーティリティクラスです。
 *
 * @author Kato Shinya
 * @version 1.0
 * @since 1.0
 */
public final class CalendarHandler {

    /**
     * 当該クラスで扱う日付の形式です。
     */
    private static final String FORMAT_DATE = "yyyyMMdd";

    /**
     * 当該クラスで扱う日時の形式です。
     */
    private static final String FORMAT_DATETIME = "yyyyMMddHHmmss";

    /**
     * 日時の形式変換を行うオブジェクト。
     * 変換形式は"yyyyMMddHHmmss"です。
     */
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMAT_DATETIME);

    /**
     * 1日をミリ秒で表した値です。
     * 当該フィールドの値は以下の演算結果を保持します。
     * <p>
     * 1, 1日 -> 24時間
     * 2, 1時間 -> 60分
     * 3, 1分 -> 60秒
     * 4, 1秒 -> 1000ミリ秒
     * <p>
     * 従って、1000×60×60×24 = 86400000
     */
    private static final long DayInMilliseconds = 86400000L;

    /**
     * 当該クラスのコンストラクタです。
     * 当該クラスはインスタンス生成を必要としないため修飾子をprivate指定しています。
     */
    private CalendarHandler() {
    }

    /**
     * 現在のクライアント日時（yyyyMMddHHmmss形式）を文字列として返却します。
     *
     * @return yyyyMMddHHmmss形式の日時。
     */
    public static String getClientDatetime() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    /**
     * 引数として渡された文字列を当該クラスで扱う日付形式に変換し文字列として返却します。
     * 第2引数には変換後の形式ではなく、変換対象の文字列で使用されている形式を指定してください。
     * 当該クラスで扱う日付形式はyyyyMMddです。
     *
     * @param date   変換対象の日付。
     * @param format 変換対象の日付で使用されている形式。
     * @return yyyyMMdd形式に変換された日付。
     */
    public static String parseDate(String date, String format) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat originalFormat = new SimpleDateFormat(format);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat parsedFormat = new SimpleDateFormat(FORMAT_DATE);

        return parsedFormat.format(originalFormat.format(date));
    }

    /**
     * 引数として渡された文字列を当該クラスで扱う日時形式に変換し文字列として返却します。
     * 第2引数には変換後の形式ではなく、変換対象の文字列で使用されている形式を指定してください。
     * 当該クラスで扱う日付形式はyyyyMMddHHmmssです。
     *
     * @param datetime 変換対象の日時。
     * @param format   変換対象の日時で使用されている形式。
     * @return yyyyMMddHHmmss形式に変換された日時。
     */
    public static String parseDatetime(String datetime, String format) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat originalFormat = new SimpleDateFormat(format);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat parsedFormat = new SimpleDateFormat(FORMAT_DATETIME);

        Date parsedOriginalDatetime = null;

        try {
            parsedOriginalDatetime = originalFormat.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parsedFormat.format(Objects.requireNonNull(parsedOriginalDatetime));
    }

    /**
     * 引数として渡された日付に加減数を足し、
     * 加減された日付を文字列として返却します。
     * <p>
     * 日付を未来へ進める場合は正の整数を指定し、
     * 日付を過去へ進める場合は負の整数を各引数に指定してください。
     * 操作する必要のない引数には0を指定してください。
     *
     * @param date            操作対象の日付。
     * @param adjustmentYear  加減する年数。
     * @param adjustmentMonth 加減する月数。
     * @param adjustmentDay   加減する日数。
     * @return 加減された日付。
     * @throws IllegalArgumentException 不正な入力値を検知した際に発生します。
     */
    public static String plusDate(
            String date,
            int adjustmentYear,
            int adjustmentMonth,
            int adjustmentDay) {

        if (!isEffectiveDate(date)) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6)) - 1;
        int day = Integer.parseInt(date.substring(6, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);

        calendar.add(Calendar.YEAR, adjustmentYear);
        calendar.add(Calendar.MONTH, adjustmentMonth);
        calendar.add(Calendar.DATE, adjustmentDay);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);

        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 引数として渡された日付の年に加減数を足し、
     * 加減された日付を文字列として返却します。
     * <p>
     * 日付を未来へ進める場合は正の整数を指定し、
     * 日付を過去へ進める場合は負の整数を引数に指定してください。
     *
     * @param date           操作対象の日付。
     * @param adjustmentYear 加減する年数。
     * @return 加減された日付。
     * @throws IllegalArgumentException 不正な入力値を検知した際に発生します。
     */
    public static String plusYear(String date, int adjustmentYear) {

        if (!isEffectiveDate(date)) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6)) - 1;
        int day = Integer.parseInt(date.substring(6, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);

        calendar.add(Calendar.YEAR, adjustmentYear);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);

        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 引数として渡された日付の月に加減数を足し、
     * 加減された日付を文字列として返却します。
     * <p>
     * 日付を未来へ進める場合は正の整数を指定し、
     * 日付を過去へ進める場合は負の整数を引数に指定してください。
     *
     * @param date            操作対象の日付。
     * @param adjustmentMonth 加減する月数。
     * @return 加減された日付。
     * @throws IllegalArgumentException 不正な入力値を検知した際に発生します。
     */
    public static String plusMonth(String date, int adjustmentMonth) {

        if (!isEffectiveDate(date)) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6)) - 1;
        int day = Integer.parseInt(date.substring(6, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);

        calendar.add(Calendar.MONTH, adjustmentMonth);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);

        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 引数として渡された日付の日に加減数を足し、
     * 加減された日付を文字列として返却します。
     * <p>
     * 日付を未来へ進める場合は正の整数を指定し、
     * 日付を過去へ進める場合は負の整数を引数に指定してください。
     *
     * @param date          操作対象の日付。
     * @param adjustmentDay 加減する日数。
     * @return 加減された日付。
     * @throws IllegalArgumentException 不正な入力値を検知した際に発生します。
     */
    public static String plusDay(String date, int adjustmentDay) {

        if (!isEffectiveDate(date)) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6)) - 1;
        int day = Integer.parseInt(date.substring(6, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day);

        calendar.add(Calendar.DATE, adjustmentDay);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE);

        return simpleDateFormat.format(calendar.getTime());
    }

    /**
     * 引数として渡された2つの入力値から経過期間を取得し数値型として返却します。
     * 基準日よりも比較対象の日付が未来にある場合は負数を返却します。
     * 当該メソッドの入力値はyyyyMMdd形式である必要があります。
     *
     * @param baseDate       基準となる日付 (yyyyMMdd形式)。
     * @param comparisonDate 比較対象の日付 (yyyyMMdd形式)。
     * @return 基準日から比較対象日付を引いた経過期間。
     * @throws IllegalArgumentException 不正な入力値を検知した際に発生します。
     */
    public static int getElapsedPeriodFromDate(String baseDate, String comparisonDate) {

        if (!isEffectiveDate(baseDate) || !isEffectiveDate(comparisonDate)) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        int baseYear = Integer.parseInt(baseDate.substring(0, 4));
        int baseMonth = Integer.parseInt(baseDate.substring(4, 6)) - 1;
        int baseDay = Integer.parseInt(baseDate.substring(6, 8));

        int comparisonYear = Integer.parseInt(comparisonDate.substring(0, 4));
        int comparisonMonth = Integer.parseInt(comparisonDate.substring(4, 6)) - 1;
        int comparisonDay = Integer.parseInt(comparisonDate.substring(6, 8));

        Calendar baseCalendar = Calendar.getInstance();
        Calendar comparisonCalendar = Calendar.getInstance();
        baseCalendar.clear();
        comparisonCalendar.clear();

        baseCalendar.set(baseYear, baseMonth, baseDay);
        comparisonCalendar.set(comparisonYear, comparisonMonth, comparisonDay);

        long elapsedTimeMs
                = baseCalendar.getTimeInMillis() - comparisonCalendar.getTimeInMillis();

        return (int) (elapsedTimeMs / DayInMilliseconds);
    }

    /**
     * 引数として渡された2つの入力値から経過期間を取得し数値型として返却します。
     * 基準日時よりも比較対象の日時が未来にある場合は負数を返却します。
     * 当該メソッドの入力値はyyyyMMddHHmmss形式である必要があります。
     *
     * @param baseDatetime       基準となる日時 (yyyyMMddHHmmss形式)。
     * @param comparisonDatetime 比較対象の日時 (yyyyMMddHHmmss形式)。
     * @return 基準日時から比較対象日時を引いた経過期間。
     * @throws IllegalArgumentException 不正な入力値を検知した際に発生します。
     */
    public static int getElapsedPeriodFromDatetime(String baseDatetime, String comparisonDatetime) {

        if (!isEffectiveDatetime(baseDatetime) || !isEffectiveDatetime(comparisonDatetime)) {
            // should not be happened
            throw new IllegalArgumentException();
        }

        int baseYear = Integer.parseInt(baseDatetime.substring(0, 4));
        int baseMonth = Integer.parseInt(baseDatetime.substring(4, 6)) - 1;
        int baseDay = Integer.parseInt(baseDatetime.substring(6, 8));
        int baseHour = Integer.parseInt(baseDatetime.substring(8, 10));
        int baseMinute = Integer.parseInt(baseDatetime.substring(10, 12));
        int baseSecond = Integer.parseInt(baseDatetime.substring(12, 14));

        int comparisonYear = Integer.parseInt(comparisonDatetime.substring(0, 4));
        int comparisonMonth = Integer.parseInt(comparisonDatetime.substring(4, 6)) - 1;
        int comparisonDay = Integer.parseInt(comparisonDatetime.substring(6, 8));
        int comparisonHour = Integer.parseInt(comparisonDatetime.substring(8, 10));
        int comparisonMinute = Integer.parseInt(comparisonDatetime.substring(10, 12));
        int comparisonSecond = Integer.parseInt(comparisonDatetime.substring(12, 14));

        Calendar baseCalendar = Calendar.getInstance();
        Calendar comparisonCalendar = Calendar.getInstance();
        baseCalendar.clear();
        comparisonCalendar.clear();

        baseCalendar.set(
                baseYear,
                baseMonth,
                baseDay,
                baseHour,
                baseMinute,
                baseSecond
        );

        comparisonCalendar.set(
                comparisonYear,
                comparisonMonth,
                comparisonDay,
                comparisonHour,
                comparisonMinute,
                comparisonSecond
        );

        long elapsedTimeMs
                = baseCalendar.getTimeInMillis() - comparisonCalendar.getTimeInMillis();

        return (int) (elapsedTimeMs / DayInMilliseconds);
    }

    /**
     * 引数として渡された文字列が以下の規則に従う形式か検査し、
     * 検査結果を真偽値として返却します。
     * <p>
     * 1, 必須チェック
     * 2, 桁数チェック (8桁)
     * 3, 日付変換チェック
     *
     * @param date 検査対象の文字列。
     * @return 日付として有効な値の場合は {@code true}、それ以外は{@code false}
     */
    private static boolean isEffectiveDate(String date) {

        if (!StringChecker.isEffectiveString(date) || date.length() != 8) {
            return false;
        }

        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6)) - 1;
        int day = Integer.parseInt(date.substring(6, 8));

        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(year, month, day);

        try {
            calendar.getTime();
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    /**
     * 引数として渡された文字列が以下の規則に従う形式か検査し、
     * 検査結果を真偽値として返却します。
     * <p>
     * 1, 必須チェック
     * 2, 桁数チェック (14桁)
     * 3, 日時変換チェック
     *
     * @param datetime 検査対象の文字列。
     * @return 日時として有効な値の場合は {@code true}、それ以外は{@code false}
     */
    private static boolean isEffectiveDatetime(String datetime) {

        if (!StringChecker.isEffectiveString(datetime) || datetime.length() != 14) {
            return false;
        }

        int year = Integer.parseInt(datetime.substring(0, 4));
        int month = Integer.parseInt(datetime.substring(4, 6)) - 1;
        int day = Integer.parseInt(datetime.substring(6, 8));
        int hour = Integer.parseInt(datetime.substring(8, 10));
        int minute = Integer.parseInt(datetime.substring(10, 12));
        int second = Integer.parseInt(datetime.substring(12, 14));

        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(year, month, day, hour, minute, second);

        try {
            calendar.getTime();
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }
}
