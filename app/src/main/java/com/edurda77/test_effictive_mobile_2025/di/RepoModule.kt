package com.edurda77.test_effictive_mobile_2025.di

import com.edurda77.data.repository.LocalRepositoryImpl
import com.edurda77.data.repository.RemoteRepositoryImpl
import com.edurda77.domain.repository.LocalRepository
import com.edurda77.domain.repository.RemoteRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repoModule = module {
    singleOf(::LocalRepositoryImpl) { bind<LocalRepository>() }
    singleOf(::RemoteRepositoryImpl) { bind<RemoteRepository>() }

}