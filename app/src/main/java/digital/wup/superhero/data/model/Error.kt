package digital.wup.superhero.data.model


data class Error(
        var code: String?,
        var message: String?) {


    companion object {
        @JvmStatic
        val EMPTY = "E001"
    }
}
