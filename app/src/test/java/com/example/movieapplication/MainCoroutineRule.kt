package com.example.movieapplication

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

//@ExperimentalCoroutinesApi
//class MainCoroutineRule : TestWatcher(),
//    TestCoroutineScope by TestCoroutineScope() {
//
//    override fun starting(description: Description) {
//        super.starting(description)
//        Dispatchers.setMain(
//            this.coroutineContext[ContinuationInterceptor] as CoroutineDispatcher
//        )
//    }
//
//    override fun finished(description: Description) {\
//        super.finished(description)
//        Dispatchers.resetMain()
//    }
//}

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher(),
    TestCoroutineScope by TestCoroutineScope(dispatcher) {
    override fun starting(description: Description) {
        super.starting(description)
        // If your codebase allows the injection of other dispatchers like
        // Dispatchers.Default and Dispatchers.IO, consider injecting all of them here
        // and renaming this class to `CoroutineScopeRule`
        //
        // All injected dispatchers in a test should point to a single instance of
        // TestCoroutineDispatcher.
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}