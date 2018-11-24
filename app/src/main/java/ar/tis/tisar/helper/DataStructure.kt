package ar.tis.tisar.helper

data class DataStructure (
  val id: Int, val firma: String, val bauleiter: String, val telefonBau: String,
  val baumassnahme: String, val grund_der_Baumassnahme: String,
  val ausfuerhrende_Baufirma: String, val ansprechpartner: String,
  val telefon: String, val baubeginn: String, val baudauer: String,
  val v1: Boolean, val v2: Boolean,
  val v3: Boolean, val v4: Boolean,
  val v5: Boolean, val v6: Boolean,
  val v7: Boolean
)