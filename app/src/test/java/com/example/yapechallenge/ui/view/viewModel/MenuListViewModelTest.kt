package com.example.yapechallenge.ui.view.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.yapechallenge.data.MenuRepository
import com.example.yapechallenge.domain.model.Menu
import com.example.yapechallenge.domain.usesCase.GetMenusUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MenuListViewModelTest {

    @RelaxedMockK
    private lateinit var getMenuUseCase: GetMenusUseCase

    private lateinit var menuListViewModel: MenuListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        menuListViewModel = MenuListViewModel(getMenuUseCase)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when menulistViewModel get all menus then set on the liveData`() = runTest {
        //Given
        val menuList = listOf<Menu>(
            Menu(
                "nombre",
                "descripcion",
                "ingrediente",
                "imagen",
                "55.254584",
                "-32.2525285"
            )
        )
        coEvery { getMenuUseCase() } returns menuList

        //When
        menuListViewModel.getAllMenus()

        //Then
        assert(menuListViewModel.isResponseOk.value == menuList)
    }

    @Test
    fun `when menulistViewModel get empty list then set null on the liveData`() = runTest {
        //Given
        val menuList = emptyList<Menu>()
        coEvery { getMenuUseCase() } returns menuList

        //When
        menuListViewModel.getAllMenus()

        //Then
        assert(menuListViewModel.isResponseOk.value == null)

    }

}