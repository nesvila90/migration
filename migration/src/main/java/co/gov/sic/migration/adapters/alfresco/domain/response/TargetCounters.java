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
        "Aspects associated",
        "Batches completed",
        "Batches submitted",
        "Bytes imported",
        "Content streamed",
        "In place content linked",
        "Metadata properties imported",
        "Nodes imported",
        "Nodes skipped",
        "Versions imported"
})
public class TargetCounters implements Serializable {
    @JsonProperty("AspectsAssociated")
    private AspectsAssociated aspectsAssociated;
    @JsonProperty("BatchesCompleted")
    private BatchesCompleted batchesCompleted;
    @JsonProperty("BatchesSubmitted")
    private BatchesSubmitted batchesSubmitted;
    @JsonProperty("BytesImported")
    private BytesImported bytesImported;
    @JsonProperty("ContentStreamed")
    private ContentStreamed contentStreamed;
    @JsonProperty("InPlaceContentLinked")
    private InPlaceContentLinked inPlaceContentLinked;
    @JsonProperty("MetadataPropertiesImported")
    private MetadataPropertiesImported metadataPropertiesImported;
    @JsonProperty("NodesImported")
    private NodesImported nodesImported;
    @JsonProperty("NodesSkipped")
    private NodesSkipped nodesSkipped;
    @JsonProperty("VersionsImported")
    private VersionsImported versionsImported;
    private final static long serialVersionUID = 7229143048066237729L;

    /**
     * No args constructor for use in serialization
     *
     */
    public TargetCounters() {
    }

    /**
     *
     * @param bytesImported
     * @param aspectsAssociated
     * @param versionsImported
     * @param metadataPropertiesImported
     * @param batchesCompleted
     * @param batchesSubmitted
     * @param inPlaceContentLinked
     * @param contentStreamed
     * @param nodesSkipped
     * @param nodesImported
     */
    public TargetCounters(AspectsAssociated aspectsAssociated, BatchesCompleted batchesCompleted, BatchesSubmitted batchesSubmitted, BytesImported bytesImported, ContentStreamed contentStreamed, InPlaceContentLinked inPlaceContentLinked, MetadataPropertiesImported metadataPropertiesImported, NodesImported nodesImported, NodesSkipped nodesSkipped, VersionsImported versionsImported) {
        super();
        this.aspectsAssociated = aspectsAssociated;
        this.batchesCompleted = batchesCompleted;
        this.batchesSubmitted = batchesSubmitted;
        this.bytesImported = bytesImported;
        this.contentStreamed = contentStreamed;
        this.inPlaceContentLinked = inPlaceContentLinked;
        this.metadataPropertiesImported = metadataPropertiesImported;
        this.nodesImported = nodesImported;
        this.nodesSkipped = nodesSkipped;
        this.versionsImported = versionsImported;
    }

    @JsonProperty("AspectsAssociated")
    public AspectsAssociated getAspectsAssociated() {
        return aspectsAssociated;
    }

    @JsonProperty("Aspects associated")
    public void setAspectsAssociated(AspectsAssociated aspectsAssociated) {
        this.aspectsAssociated = aspectsAssociated;
    }

    @JsonProperty("BatchesCompleted")
    public BatchesCompleted getBatchesCompleted() {
        return batchesCompleted;
    }

    @JsonProperty("Batches completed")
    public void setBatchesCompleted(BatchesCompleted batchesCompleted) {
        this.batchesCompleted = batchesCompleted;
    }

    @JsonProperty("BatchesSubmitted")
    public BatchesSubmitted getBatchesSubmitted() {
        return batchesSubmitted;
    }

    @JsonProperty("Batches submitted")
    public void setBatchesSubmitted(BatchesSubmitted batchesSubmitted) {
        this.batchesSubmitted = batchesSubmitted;
    }

    @JsonProperty("BytesImported")
    public BytesImported getBytesImported() {
        return bytesImported;
    }

    @JsonProperty("Bytes imported")
    public void setBytesImported(BytesImported bytesImported) {
        this.bytesImported = bytesImported;
    }

    @JsonProperty("ContentStreamed")
    public ContentStreamed getContentStreamed() {
        return contentStreamed;
    }

    @JsonProperty("Content streamed")
    public void setContentStreamed(ContentStreamed contentStreamed) {
        this.contentStreamed = contentStreamed;
    }

    @JsonProperty("InPlaceContentLinked")
    public InPlaceContentLinked getInPlaceContentLinked() {
        return inPlaceContentLinked;
    }

    @JsonProperty("In place content linked")
    public void setInPlaceContentLinked(InPlaceContentLinked inPlaceContentLinked) {
        this.inPlaceContentLinked = inPlaceContentLinked;
    }

    @JsonProperty("MetadataPropertiesImported")
    public MetadataPropertiesImported getMetadataPropertiesImported() {
        return metadataPropertiesImported;
    }

    @JsonProperty("Metadata properties imported")
    public void setMetadataPropertiesImported(MetadataPropertiesImported metadataPropertiesImported) {
        this.metadataPropertiesImported = metadataPropertiesImported;
    }

    @JsonProperty("NodesImported")
    public NodesImported getNodesImported() {
        return nodesImported;
    }

    @JsonProperty("Nodes imported")
    public void setNodesImported(NodesImported nodesImported) {
        this.nodesImported = nodesImported;
    }

    @JsonProperty("NodesSkipped")
    public NodesSkipped getNodesSkipped() {
        return nodesSkipped;
    }

    @JsonProperty("Nodes skipped")
    public void setNodesSkipped(NodesSkipped nodesSkipped) {
        this.nodesSkipped = nodesSkipped;
    }

    @JsonProperty("VersionsImported")
    public VersionsImported getVersionsImported() {
        return versionsImported;
    }

    @JsonProperty("Versions imported")
    public void setVersionsImported(VersionsImported versionsImported) {
        this.versionsImported = versionsImported;
    }
}
