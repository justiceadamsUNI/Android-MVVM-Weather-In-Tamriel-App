package weatherintamriel.module

import dagger.Module
import dagger.Provides
import weatherintamriel.api.ZipCodeApi
import weatherintamriel.api.repository.ZipCodeInformationRepository
import javax.inject.Singleton

@Module
class ZipCodeInformationRepositoryModule {

    @Provides
    @Singleton
    fun provideZipCodeInformationRepository(zipCodeApi: ZipCodeApi): ZipCodeInformationRepository {
        return ZipCodeInformationRepository(zipCodeApi)
    }
}