[#ftl]
[#macro stateToHtmlColour state="Never run"]
  [@compress single_line=true]
    [#if     state="Scanning"]   darkcyan
    [#elseif state="Importing"]  darkblue
    [#elseif state="Paused"]     darkgray
    [#elseif state="Stopping"]   orange
    [#elseif state="Never run"]  black
    [#elseif state="Successful"] green
    [#elseif state="Failed"]     red
    [#elseif state="Stopped"]    orange
    [#else]                      black
    [/#if]
  [/@compress]
[/#macro]
<!DOCTYPE HTML>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600' rel='stylesheet' type='text/css'>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>M&oacute;dulo de cargue de Documentos - Estado</title>
    <meta name="description" content="M&oacute;dulo de cargue de Documentos - Estado">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    [#-- 3rd Party Stuff --]
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-2.2.4.js"></script>
    <script src="//code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/loglevel/1.4.0/loglevel.min.js"></script>
    <script src="${url.context}/scripts/bulkimport/modernizr-3.3.1.min.js"></script>
    <script src="${url.context}/scripts/bulkimport/favicon.min.js"></script>
    <script src="${url.context}/scripts/bulkimport/smoothie.js"></script>
    [#-- Bulk import --]
    <link rel="stylesheet" href="${url.context}/css/bulkimport/normalize.css">
    <link rel="stylesheet" href="${url.context}/css/bulkimport/main.css">
    <link rel="stylesheet" href="${url.context}/css/bulkimport/bulkimport.css">
    <script src="${url.context}/scripts/bulkimport/bulkimport.js"></script>
</head>
<body>
    <!--[if lt IE 9]>
        <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <div class="container">
      <div class="block">
        <img style="margin:15px;vertical-align:middle" src="${url.context}/images/bulkimport/logo.png" alt="M&oacute;dulo de cargue de Documentos" />
      </div>
      <div class="block">
        <h1><strong>M&oacute;dulo de cargue de Documentos - Estado</strong></h1>
      </div>
    </div>

[#if importStatus.inProgress()]
    <div style="display:inline-block;height:50px;font-size:16pt">
      <div id="currentStatus" style="display:inline-block;color:black;bold">En progreso ${(importStatus.duration!"")?html}</div>
    </div>
    <p>
  [#if importStatus.isPaused()]
      <button id="pauseImportButton" style="display:none" class="button orange" type="button">&#10074;&#10074; Pausar</button>
      <button id="resumeImportButton" style="display:inline" class="button green" type="button">&#9658; Reanudar</button>
  [#else]
      <button id="pauseImportButton" style="display:inline" class="button orange" type="button">&#10074;&#10074; Pausar</button>
      <button id="resumeImportButton" style="display:none" class="button green" type="button">&#9658; Reanudar</button>
  [/#if]
      <button id="stopImportButton" style="display:inline" class="button red" type="button">&#9724; Detener</button>
      <a id="initiateAnotherImport" style="display:none" href="${url.serviceContext}/bulk/import">Iniciar otra importaci&oacute;n</a>
    </p>
[#else]
    <div style="display:inline-block;height:50px;font-size:16pt">
      <div id="currentStatus" style="display:inline-block;color:[@stateToHtmlColour importStatus.processingState/];bold">${(importStatus.processingState!"")?html}</div><div id="estimatedDuration" style="display:inline-block;"></div>
    </div>
    <p>
      <button id="pauseImportButton" style="display:none" class="button orange" type="button">&#10074;&#10074; Pausar</button>
      <button id="resumeImportButton" style="display:none" class="button green" type="button">&#9658; Reanudar</button>
      <button id="stopImportButton" style="display:none" class="button red" type="button">&#9724; Detener</button>
      <a id="initiateAnotherImport" href="${url.serviceContext}/bulk/import">Iniciar otra importaci&oacute;n</a>
    </p>
[/#if]

    <div id="accordion">
      <h3>Detalle</h3>
      <div>
        <table border="1" cellspacing="0" cellpadding="1" width="80%">
          <thead>
            <tr>
              <th colspan="2">Estadisticas Generales</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td width="25%">Estado:</td>
              <td width="75%" id="detailsStatus" style="color:[@stateToHtmlColour importStatus.processingState/]">${(importStatus.processingState!"")?html}</td>
            </tr>
            <tr>
              <td>Usuario:</td>
              <td>${(importStatus.initiatingUserId!"n/a")?html}</td>
            </tr>
            <tr>
              <td>Origen:</td>
              <td>${(importStatus.sourceName!"n/a")?html}</td>
            </tr>
[#if importStatus.sourceParameters??]
  [#list importStatus.sourceParameters?keys as sourceParameterKey]
    [#assign sourceParameterValue = importStatus.sourceParameters[sourceParameterKey]]
            <tr>
              <td>${(sourceParameterKey!"n/a")?html}</td>
              <td>${(sourceParameterValue!"n/a")?string?html}</td>
            </tr>
  [/#list]
[/#if]
            <tr>
              <td>Espacio Destino:</td>
              <td>${(importStatus.targetPath!"n/a")?html}</td>
            </tr>
            <tr>
              <td>Tipo Importaci&oacute;n:</td>
              <td>[#if importStatus.neverRun()]n/a[#elseif importStatus.inPlaceImportPossible()]En Servidor[#else]Streaming[/#if]</td>
            </tr>
            <tr>
              <td>Probar Solamente:</td>
              <td>[#if importStatus.neverRun()]n/a[#elseif importStatus.dryRun]Si[#else]No[/#if]</td>
            </tr>
            <tr>
              <td>Peso del Lote:</td>
              <td>[#if importStatus.neverRun()]n/a[#else]${importStatus.batchWeight}[/#if]</td>
            </tr>
            <tr>
              <td>Lotes en Cola:</td>
              <td><span id="detailsQueueSize">[#if importStatus.neverRun()]0[#else]${importStatus.queueSize}[/#if]</span>
                  de un maximo de ${importStatus.queueCapacity}</td>
            </tr>
            <tr>
              <td>Hilos:</td>
              <td><span id="detailsActiveThreads">[#if importStatus.neverRun()]0[#else]${importStatus.numberOfActiveThreads}[/#if]</span>
                  activos de <span id="detailsTotalThreads">[#if importStatus.neverRun()]0[#else]${importStatus.totalNumberOfThreads}[/#if]</span> total</td>
            </tr>
            <tr>
              <td>Fecha Inicial:</td>
              <td>[#if importStatus.startDate??]${importStatus.startDate?datetime?iso_utc}[#else]n/a[/#if]</td>
            </tr>
            <tr>
              <td>Fecha de escaneo final:</td>
              <td id="detailsScanEndDate">[#if importStatus.scanEndDate??]${importStatus.scanEndDate?datetime?iso_utc}[#else]n/a[/#if]</td>
            </tr>
            <tr>
              <td>Fecha Final:</td>
              <td id="detailsEndDate">[#if importStatus.endDate??]${importStatus.endDate?datetime?iso_utc}[#else]n/a[/#if]</td>
            </tr>
            <tr>
              <td>Duraci&oacute;n del Escaneo:</td>
              <td id="detailsScanDuration">${(importStatus.scanDuration!"n/a")?html}</td>
            </tr>
            <tr>
              <td>Duraci&oacute;n:</td>
              <td id="detailsDuration">${(importStatus.duration!"n/a")?html}</td>
            </tr>
            <tr>
              <td>Importaci&oacute;n Actual:</td>
              <td id="detailsCurrentlyImporting">${(importStatus.currentlyImporting!"n/a")?html}</td>
            </tr>
          </tbody>
        </table>

        <br/>

[#-- SOURCE COUNTERS --]
        <table id="sourceCounterTable" border="1" cellspacing="0" cellpadding="1" width="80%">
          <thead>
            <tr>
              <th colspan="3">Estad&iacute;sticas Destino (Lectura)</th>
            </tr>
          </thead>
          <tbody id="sourceCounterTableBody">
[#if importStatus.neverRun() || !importStatus.sourceCounters??]
            <tr>
              <td colspan="3">n/a</td>
            </tr>
[#else]
  [#list importStatus.sourceCounters?keys as counterKey]
    [#assign count = importStatus.sourceCounters[counterKey].Count]
    [#assign rate  = importStatus.sourceCounters[counterKey].Rate]
            <tr>
              <td width="25%">${counterKey?html}</td>
              <td width="35%">${count}</td>
              <td width="40%">${rate} / sec</td>
            </tr>
  [/#list]
[/#if]
          </tbody>
        </table>

        <br/>

[#-- TARGET COUNTERS --]
        <table id="targetCounterTable" border="1" cellspacing="0" cellpadding="1" width="80%">
          <thead>
            <tr>
              <th colspan="3">Estad&iacute;sticas Destino (Escritura)</th>
            </tr>
          </thead>
          <tbody id="targetCounterTableBody">
[#if importStatus.neverRun() || !importStatus.targetCounters??]
            <tr>
              <td colspan="3">n/a</td>
            </tr>
[#else]
  [#list importStatus.targetCounters?keys as counterKey]
    [#assign count = importStatus.targetCounters[counterKey].Count]
    [#assign rate  = importStatus.targetCounters[counterKey].Rate]
            <tr>
              <td width="25%">${counterKey}</td>
              <td width="35%">${count}</td>
              <td width="40%">${rate} / sec</td>
            </tr>
  [/#list]
[/#if]
          </tbody>
        </table>

[#-- ERROR INFORMATION --]
[#if importStatus.lastExceptionAsString??]
        <div id="detailsErrorInformation" style="display:block">
[#else]
        <div id="detailsErrorInformation" style="display:none">
[/#if]
          <br/>
          <table border="1" cellspacing="0" cellpadding="1" width="80%">
            <thead>
              <tr>
                <th colspan="2">Informaci&oacute;n de Error</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Exception:</td>
                <td><pre style="font-size:8pt" id="detailsLastException">${importStatus.lastExceptionAsString!"n/a"}</pre></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>  [#-- End of accordion --]

    <p>Nota: Puede cerrar esta pagina en cualquier momento. El proceso se seguir&aacute; ejecutando en el servidor.</p>

    <hr/>
    <p class="footnote">Herramienta de Cargue versi&oacute;n 2.1.1, Alfresco ${server.edition} v${server.version}</p>
<script>
  $(document).ready(function() {
    initStatus('${url.context?js_string}', '${url.serviceContext?js_string}');

    $("#accordion").accordion({
      active: 0,
      heightStyle: "content"
    });

    $("#pauseImportButton").button().click(function() {
      pauseImport();
    });

    $("#resumeImportButton").button().click(function() {
      resumeImport();
    });

    $("#stopImportButton").button().click(function() {
      stopImport();
    });
  });
</script>

</body>
</html>
