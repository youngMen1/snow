package com.snow.snowcore.config;

/**
 * @author zhiqiang.feng
 * @version 1.0
 * @date-time 2019/3/27 19:35
 * @description
 **/
public class SwaggerApiInfo {
    private String title;
    private String version;
    private String description;
    private String termsOfServiceUrl;
    private String contactName;
    private String contactUrl;
    private String contactEmail;

    SwaggerApiInfo(final String title, final String version, final String description, final String termsOfServiceUrl, final String contactName, final String contactUrl, final String contactEmail) {
        this.title = title;
        this.version = version;
        this.description = description;
        this.termsOfServiceUrl = termsOfServiceUrl;
        this.contactName = contactName;
        this.contactUrl = contactUrl;
        this.contactEmail = contactEmail;
    }

    public static SwaggerApiInfo.SwaggerApiInfoBuilder builder() {
        return new SwaggerApiInfo.SwaggerApiInfoBuilder();
    }

    public String getTitle() {
        return this.title;
    }

    public String getVersion() {
        return this.version;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTermsOfServiceUrl() {
        return this.termsOfServiceUrl;
    }

    public String getContactName() {
        return this.contactName;
    }

    public String getContactUrl() {
        return this.contactUrl;
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    @Override
    public String toString() {
        return "SwaggerApiInfo(super=" + super.toString() + ", title=" + this.getTitle() + ", version=" + this.getVersion() + ", description=" + this.getDescription() + ", termsOfServiceUrl=" + this.getTermsOfServiceUrl() + ", contactName=" + this.getContactName() + ", contactUrl=" + this.getContactUrl() + ", contactEmail=" + this.getContactEmail() + ")";
    }

    public static class SwaggerApiInfoBuilder {
        private String title;
        private String version;
        private String description;
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;

        SwaggerApiInfoBuilder() {
        }

        public SwaggerApiInfo.SwaggerApiInfoBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public SwaggerApiInfo.SwaggerApiInfoBuilder version(final String version) {
            this.version = version;
            return this;
        }

        public SwaggerApiInfo.SwaggerApiInfoBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public SwaggerApiInfo.SwaggerApiInfoBuilder termsOfServiceUrl(final String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
            return this;
        }

        public SwaggerApiInfo.SwaggerApiInfoBuilder contactName(final String contactName) {
            this.contactName = contactName;
            return this;
        }

        public SwaggerApiInfo.SwaggerApiInfoBuilder contactUrl(final String contactUrl) {
            this.contactUrl = contactUrl;
            return this;
        }

        public SwaggerApiInfo.SwaggerApiInfoBuilder contactEmail(final String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }

        public SwaggerApiInfo build() {
            return new SwaggerApiInfo(this.title, this.version, this.description, this.termsOfServiceUrl, this.contactName, this.contactUrl, this.contactEmail);
        }
        @Override
        public String toString() {
            return "SwaggerApiInfo.SwaggerApiInfoBuilder(title=" + this.title + ", version=" + this.version + ", description=" + this.description + ", termsOfServiceUrl=" + this.termsOfServiceUrl + ", contactName=" + this.contactName + ", contactUrl=" + this.contactUrl + ", contactEmail=" + this.contactEmail + ")";
        }
    }
}
