package digital.wup.superhero.domain

abstract class UseCase<Rq : Request, Rs : Response> {
    var request: Rq? = null
    var useCaseCallback: UseCaseCallback<Rs>? = null

    fun run() {
        this.executeUseCase(request)
    }

    abstract fun executeUseCase(request: Rq?)
}
