package digital.wup.superhero.module

import android.content.Context

import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import digital.wup.superhero.data.CharactersDataStore
import digital.wup.superhero.data.CharactersLocalDataSource
import digital.wup.superhero.data.CharactersRemoteDataStore
import digital.wup.superhero.data.CharactersRepository
import digital.wup.superhero.data.network.NetworkInterceptor
import digital.wup.superhero.domain.UseCaseHandler
import digital.wup.superhero.domain.UseCaseScheduler
import digital.wup.superhero.domain.UseCaseThreadPoolScheduler
import digital.wup.superhero.domain.usecase.GetCharacterUseCase
import digital.wup.superhero.domain.usecase.GetCharactersUseCase
import digital.wup.superhero.presentation.ui.characters.CharacatersPresenterImpl
import digital.wup.superhero.presentation.ui.characters.CharactersContract
import digital.wup.superhero.presentation.ui.details.DetailsContract
import digital.wup.superhero.presentation.ui.details.DetailsPresenterImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class SuperheroModule(private val applicationContext: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return applicationContext
    }

    @Singleton
    @Provides
    fun providCharactersLocalDataSource(context: Context): CharactersLocalDataSource {
        return CharactersLocalDataSource(context)
    }

    @Singleton
    @Provides
    fun providCharactersRemoteDataStore(okHttpClient: OkHttpClient): CharactersRemoteDataStore {
        return CharactersRemoteDataStore(okHttpClient)
    }

    @Singleton
    @Provides
    fun providCharactersDataStore(remoteDataStore: CharactersRemoteDataStore, localDataSource: CharactersLocalDataSource): CharactersDataStore {
        return CharactersRepository(remoteDataStore, localDataSource)
    }

    @Singleton
    @Provides
    fun providGetCharactersUseCase(charactersDataStore: CharactersDataStore): GetCharactersUseCase {
        return GetCharactersUseCase(charactersDataStore)
    }

    @Singleton
    @Provides
    fun provideUseCaseScheduler(): UseCaseScheduler {
        return UseCaseThreadPoolScheduler()
    }

    @Singleton
    @Provides
    fun provideUseCaseHandler(useCaseScheduler: UseCaseScheduler): UseCaseHandler {
        return UseCaseHandler(useCaseScheduler)
    }

    @Singleton
    @Provides
    fun provideCharactersPresenter(useCase: GetCharactersUseCase, useCaseHandler: UseCaseHandler): CharactersContract.CharactersPresenter {
        return CharacatersPresenterImpl(useCase, useCaseHandler)
    }

    @Singleton
    @Provides
    fun provideGetCharacterUseCase(dataStore: CharactersDataStore): GetCharacterUseCase {
        return GetCharacterUseCase(dataStore)
    }

    @Singleton
    @Provides
    fun provideDetailsPresenter(useCase: GetCharacterUseCase, useCaseHandler: UseCaseHandler): DetailsContract.DetailsPresenter {
        return DetailsPresenterImpl(useCase, useCaseHandler)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(NetworkInterceptor())
                .addInterceptor(interceptor)
                .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun providePicasso(context: Context, client: OkHttpClient): Picasso {
        return Picasso.Builder(context).downloader(OkHttp3Downloader(client)).build()
    }
}
