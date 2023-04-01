package com.example.yapechallenge.domain.usesCase

import com.example.yapechallenge.data.MenuRepository
import com.example.yapechallenge.domain.model.Menu
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMenusUseCaseTest {

    @RelaxedMockK
    private lateinit var menuRepository: MenuRepository

    lateinit var getMenuUC: GetMenusUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getMenuUC = GetMenusUseCase(menuRepository)
    }

    @Test
    fun `when the api does not return anything then return empty list`() = runBlocking {
        //Given
        coEvery { menuRepository.getAllMenusFromApi() } returns emptyList()

        //When
        getMenuUC()
        //Then
        coVerify(exactly = 1) { menuRepository.getAllMenusFromDatabase() } // verifico que se llame al metodo del else
        coVerify(exactly = 0) { menuRepository.deleteAllMenus() } // verifico que no se llame al primer metodo del if
        coVerify(exactly = 0) { menuRepository.insertMenus(any()) } // verifico que no se llame al segundo metodo del if
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
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
        coEvery { menuRepository.getAllMenusFromApi() } returns menuList

        //When
        val response = getMenuUC()

        //Then
        coVerify(exactly = 1) { menuRepository.deleteAllMenus() } // verifico que se llame al primer metodo del if
        coVerify(exactly = 1) { menuRepository.insertMenus(any()) } // verifico que se llame al segundo metodo del if
        coVerify(exactly = 0) { menuRepository.getAllMenusFromDatabase() } // verifico que no se llama el metodo del else
        assert(menuList == response) // verifico que la lista sea igual a la respuesta
    }

}