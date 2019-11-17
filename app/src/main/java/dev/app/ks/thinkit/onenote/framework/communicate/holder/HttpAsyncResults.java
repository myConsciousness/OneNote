package dev.app.ks.thinkit.onenote.framework.communicate.holder;

import java.util.List;

import dev.app.ks.thinkit.onenote.framework.communicate.property.HttpStatusCode;
import dev.app.ks.thinkit.onenote.framework.model.holder.ModelAccessor;

public final class HttpAsyncResults {

    private final HttpStatusCode httpStatusCode;

    private final List<? extends ModelAccessor> modelAccessorList;

    public HttpAsyncResults(
            HttpStatusCode httpStatusCode,
            List<? extends ModelAccessor> modelAccessorList) {

        this.httpStatusCode = httpStatusCode;
        this.modelAccessorList = modelAccessorList;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public List<? extends ModelAccessor> getModelAccessorList() {
        return modelAccessorList;
    }

    public boolean isHttpStatusOk() {
        return httpStatusCode == HttpStatusCode.HTTP_OK;
    }
}
