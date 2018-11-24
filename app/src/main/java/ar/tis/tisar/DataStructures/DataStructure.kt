package ar.tis.tisar.DataStructures

import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import java.io.StringReader

data class DataStructure (val id: Int, val firma: String, val abteilung: String, val bauleiter: String, val telefonBau: String,
                     val baumassnahme: String, val beteiligte_Sparten: String,
                     val technische_Beschreibung: String, val grund_der_Baumassnahme: String,
                     val kosten: String, val ausfuerhrende_Baufirma: String, val ansprechpartner: String,
                     val telefon: String, val baubeginn: String, val baudauer: String, val vollsperrung: Boolean,
                     val teilsperrung_mit_Anlieger_frei: Boolean, val eingeschraenkte_Parkmoeglichkeiten: Boolean,
                     val umleitung_Geh_und_Radverkehr: Boolean, val einspurige_Verkehrsfuehrung: Boolean,
                     val baustellenampelregelung: Boolean, val großraumige_Umleitung_fuer_Durchgangsverkehr: Boolean)


val jsonData = """
    {
      "Auftraggeber": {
        "id": 111,
        "firma": "NGN",
        "abteilung": "NGN MBH, 6ETT",
        "bauleiter": "Herr Weyer",
        "telefonBau": "982725"
      },
      "Bauinformationen": {
        "baumassahme": "P-18-025 Floßstraße Umlegung",
        "beteiligte_Sparten": "EWG",
        "technische_Beschreibung": "Leerrohre über eine Länge von rund 100 Metern zum Einzug von neuen Elektrizitätsleitungen",
        "grund_der_Baumassnahme": "Neugestaltung des Karlsplatzes",
        "kosten": "300.000 €"
      },
      "Ausfuerhrende_Baufirma": {
        "name": "LTG",
        "ansprechpartner": "Herr Köse",
        "telefon": "02842 55000555",
        "baubeginn": "11.06.2018",
        "baudauer": "8 Monate",
        "Verkehrsbeeinflussung": {
          "vollsperrung": false,
          "teilsperrung_mit_Anlieger_frei": true,
          "eingeschraenkte_Parkmoeglichkeiten": true,
          "umleitung_Geh_und_Radverkehr": false,
          "einspurige_Verkehrsfuehrung": true,
          "baustellenampelregelung": true,
          "großraumige_Umleitung_fuer_Durchgangsverkehr": false
        }
      }
    }
    """

fun streamingArray() {
    val klaxon = Klaxon()
    JsonReader(StringReader(jsonData)).use { reader ->
        val auftraggeber = arrayListOf<DataStructure>()
        reader.beginObject() {
            reader.beginObject {
                var id: Int? = null
                var firma: String? = null
                var abteilung: String? = null
                var bauleiter: String? = null
                var telefonBau: String? = null
                while (reader.hasNext()) {
                    val dataObject = reader.nextName()
                    when (dataObject) {
                        "id" -> id = reader.nextInt()
                        "firma" -> firma = reader.nextString()
                        "abteilung" -> abteilung = reader.nextString()
                        "bauleiter" -> bauleiter = reader.nextString()
                        "telefonbau" -> telefonBau = reader.nextString()
                    }
                }
            }
        }
    }
}

fun main() {
    streamingArray();
}