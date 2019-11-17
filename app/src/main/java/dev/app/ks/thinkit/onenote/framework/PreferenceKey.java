package dev.app.ks.thinkit.onenote.framework;

public enum PreferenceKey implements IPreferenceKey {
    SecretKey(Key.secret_key),
    GeneralDataProtectionRegulation(Key.general_data_protection_regulation),
    CountTransferForInterstitial(Key.count_transfer_for_interstitial),
    AgeVerification(Key.age_verification);

    private final Key key;

    PreferenceKey(Key key) {
        this.key = key;
    }

    @Override
    public String getKeyName() {
        return key.name();
    }

    private enum Key {
        secret_key,
        general_data_protection_regulation,
        count_transfer_for_interstitial,
        age_verification,
    }
}
