package com.example.dagger.components

import com.example.dagger.FirstFragment
import com.example.dagger.module.StringModule
import com.example.dagger.module.TestModule
import dagger.Component

@Component(modules = [StringModule::class, TestModule::class])
interface Component {
    fun inject(fragment: FirstFragment?)
}