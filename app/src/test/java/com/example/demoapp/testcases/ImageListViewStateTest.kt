package com.example.demoapp.testcases

import com.example.demoapp.data.network.Status
import com.example.demoapp.ui.ImageListViewState
import org.junit.Test
import org.junit.Assert.*
class ImageListViewStateTest {

    @Test
    fun `should show loading when status is loading`() {
        val viewState = ImageListViewState(status = Status.LOADING)

        val loading = viewState.isLoading()

        assertTrue(loading)

    }

    @Test
    fun `should not show loading when status is success`() {
        val viewState = ImageListViewState(status = Status.SUCCESS)

        val loading = viewState.isLoading()

        assertFalse(loading)

    }

    @Test
    fun `should not  show loading when status is error`() {
        val viewState = ImageListViewState(status = Status.ERROR)

        val loading = viewState.isLoading()

        assertFalse(loading)

    }

    @Test
    fun `should return expected error message when status is error`() {
        val expectedErrorMessage = "400 Bad Request"
        val viewState = ImageListViewState(status = Status.ERROR, error = Exception(expectedErrorMessage))

        val actualErrorMessage = viewState.getErrorMessage()

        assertEquals(expectedErrorMessage, actualErrorMessage)

    }

    @Test
    fun `should return true for error visibility when status is error`() {
        val viewState = ImageListViewState(status = Status.ERROR, error = Exception("400 Bad Request"))

        val errorVisibility = viewState.shouldShowError()

        assertTrue(errorVisibility)

    }
}