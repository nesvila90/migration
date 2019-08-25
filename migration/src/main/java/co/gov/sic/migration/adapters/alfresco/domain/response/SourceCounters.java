package co.gov.sic.migration.adapters.alfresco.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Files scanned"
})
public class SourceCounters implements Serializable {
    @JsonProperty("Files scanned")
    private FilesScanned filesScanned;
    private final static long serialVersionUID = -8396001193342456601L;

    /**
     * No args constructor for use in serialization
     *
     */
    public SourceCounters() {
    }

    /**
     *
     * @param filesScanned
     */
    public SourceCounters(FilesScanned filesScanned) {
        super();
        this.filesScanned = filesScanned;
    }

    @JsonProperty("Files scanned")
    public FilesScanned getFilesScanned() {
        return filesScanned;
    }

    @JsonProperty("Files scanned")
    public void setFilesScanned(FilesScanned filesScanned) {
        this.filesScanned = filesScanned;
    }
}
