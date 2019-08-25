package co.gov.sic.migration.adapters.alfresco.cmis.dao.query;

public interface CmisQuery {

    String STANDARD_LOCATION_DOCUMENT_SERIE = "SELECT ? FROM ? WHERE ? = ? AND ? = ? AND ? = ?";
    String STANDARD_LOCATION_DOCUMENT_SERIE_SUBSERIE = "SELECT ? FROM ? WHERE ? = ? AND ? = ? AND ? = ? AND ? = ?";
    String SEARCH_PARAMS_DOCUMENT_LOCATION[] = {"cmcor:CodigoUnidadAdminPadre", "cmcor:CodigoDependencia", "cmcor:CodigoSerie", "cmcor:CodigoSubserie"};


}
