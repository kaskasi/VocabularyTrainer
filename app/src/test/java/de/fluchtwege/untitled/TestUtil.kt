package de.fluchtwege.untitled

import org.mockito.Mockito

class TestUtil {
    companion object {
        fun <T> anyObject(): T {
            Mockito.anyObject<T>()
            return uninitialized()
        }

        private fun <T> uninitialized(): T = null as T
    }

}