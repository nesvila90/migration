
package co.gov.sic.migration.adapters.alfresco.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Source Directory"
})
public class SourceParameters implements Serializable
{

    @JsonProperty("SourceDirectory")
    private String sourceDirectory;
    private final static long serialVersionUID = -1741660717320720277L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SourceParameters() {
    }

    /**
     * 
     * @param sourceDirectory
     */
    public SourceParameters(String sourceDirectory) {
        super();
        this.sourceDirectory = sourceDirectory;
    }

    @JsonProperty("SourceDirectory")
    public String getSourceDirectory() {
        return sourceDirectory;
    }

    @JsonProperty("Source Directory")
    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

}
