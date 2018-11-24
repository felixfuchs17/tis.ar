package ar.tis.tisar.DataStructures

data class DataStructure (val id: Int, val firma: String, val abteilung: String, val bauleiter: String, val telefonBau: String,
                     val baumassnahme: String, val beteiligte_Sparten: String,
                     val technische_Beschreibung: String, val grund_der_Baumassnahme: String,
                     val kosten: String, val ausfuerhrende_Baufirma: String, val ansprechpartner: String,
                     val telefon: String, val baubeginn: String, val baudauer: String, val vollsperrung: Boolean,
                     val teilsperrung_mit_Anlieger_frei: Boolean, val eingeschraenkte_Parkmoeglichkeiten: Boolean,
                     val umleitung_Geh_und_Radverkehr: Boolean, val einspurige_Verkehrsfuehrung: Boolean,
                     val baustellenampelregelung: Boolean, val großraumige_Umleitung_fuer_Durchgangsverkehr: Boolean)
