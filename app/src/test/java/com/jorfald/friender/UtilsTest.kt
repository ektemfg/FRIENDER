package com.jorfald.friender

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.core.content.ContextCompat
import com.jorfald.friender.model.dataClasses.FriendEmployment
import com.jorfald.friender.model.dataClasses.FriendLocation
import com.jorfald.friender.utils.Utils
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UtilsTest {

    // Mocke context, resources og applikasjon slik at vi kan bruke/isolere dem i testing.
    @Mock
      var context: Context = mock(Context::class.java)
      var resources: Resources = mock(Resources::class.java)
      var app: Application = mock(Application::class.java)
        @Before
  fun setupMocks() {
      MockitoAnnotations.initMocks(this)
      `when`(context.resources).thenReturn(resources)
      `when`(context.resources.getColor(R.color.blue)).thenReturn(R.color.blue)
      `when`(context.resources.getColor(R.color.pink)).thenReturn(R.color.pink)
      `when`(context.resources.getColor(R.color.purple_700)).thenReturn(R.color.purple_700)

            // Får ikke til å mocke getDrawable... Så test for getGenderIcon lar seg ikke lage dessverre.
            /*
            `when`(context.resources.getDrawable(R.drawable.ic_male)).thenReturn(R.drawable.ic_male)
            `when`(context.resources.getDrawable(R.drawable.ic_female)).thenReturn(R.drawable.ic_female)
            `when`(context.resources.getDrawable(R.drawable.other)).thenReturn(R.drawable.other)

             */
  }

    @Test
    fun getAge_returns_correct_age() {

        val thisYear = 2022
        val dateOfBirth = "1999-01-01"

        val result = Utils.getAge(thisYear, dateOfBirth).toInt()
        assertEquals(23, result)
    }

    @Test
    fun getFullName_returns_fullName() {

        val firstName = "Marco"
        val lastName = "Polo"

        val result = Utils.getFullName(firstName, lastName)
        assertEquals("Marco Polo", result)
    }

    @Test
    fun isMale_result_behaves_as_expected() {

        val male = "male"
        val female = "female"
        val bigender = "bigender"
        val genderfluid = "genderfluid"
        val nonBinary = "non-binary"
        val polygender = "bigender"
        val agender = "agender"
        val other = "else"
        val resultMale = Utils.isMale(male)
        val resultFemale = Utils.isMale(female)
        val resultBigender = Utils.isMale(bigender)
        val resultGenderfluid = Utils.isMale(genderfluid)
        val resultNonBinary = Utils.isMale(nonBinary)
        val resultPolygender = Utils.isMale(polygender)
        val resultAgender = Utils.isMale(agender)
        val resultOther = Utils.isMale(other)
        // Man skal egentlig ikke ha flere tester i en test, men tror dette er okey.
        assertEquals("male", resultMale)
        assertEquals("female", resultFemale)
        assertEquals("male", resultBigender)
        assertEquals("else", resultGenderfluid)
        assertEquals("else", resultNonBinary)
        assertEquals("male", resultPolygender)
        assertEquals("else", resultAgender)
        assertEquals("else", resultOther)
    }

    @Test
    fun getEmploymentText_returns_full_employmentText() {
        val employmentGotJob = FriendEmployment("Pentester", "SQL-DB")
        val employmentNoJob = null

        val resultGotJob = Utils.getEmploymentText(employmentGotJob)
        val resultGotNoJob = Utils.getEmploymentText(employmentNoJob)

        // Expected values
        val gotJob = "Role: ${employmentGotJob.title}\n" +
                "Skill: ${employmentGotJob.key_skill}"
        val noJob = "Unemployed"

        assertEquals(gotJob, resultGotJob)
        assertEquals(noJob, resultGotNoJob)
    }

    @Test
    fun getPlaceText_returns_place() {
        val address: FriendLocation = FriendLocation("Beograd", "Serbia")
        val result = Utils.getPlaceText(address)

        // Expected values
        val expected = "Beograd, Serbia"

        assertEquals(expected, result)

    }
/*
Jeg har prøvd, men av en eller annen grunn får jeg ikke til å mocke/stubbe drawables.
    @Test

    fun getGenderIcon_returns_drawable() {
        val genderMale = "male"
        val genderFemale = "female"
        val genderElse = "alien"
        val resultMale = Utils.getGenderIcon(context, genderMale)
        val resultFemale = Utils.getGenderIcon(context, genderFemale)
        val resultElse = Utils.getGenderIcon(context, genderElse)

        // Expected
        val expectedMale = R.drawable.ic_male
        val expectedFemale = R.drawable.ic_female
        val expectedElse = R.drawable.other
        // Asserts

        assertEquals(expectedMale, resultMale)
        assertEquals(expectedFemale, resultFemale)
        assertEquals(expectedElse, resultElse)

    }

 */
    @Test
    fun getGenderColor_returns_drawable() {
        val genderMale = "male"
        val genderFemale = "female"
        val genderElse = "alien"

        val resultMale = Utils.getGenderColor(context, genderMale)
        val resultFemale = Utils.getGenderColor(context, genderFemale)
        val resultElse = Utils.getGenderColor(context, genderElse)

        // Expected
        val expectedMale = ContextCompat.getColor(context, R.color.blue)
        val expectedFemale = ContextCompat.getColor(context, R.color.pink)
        val expectedElse = ContextCompat.getColor(context, R.color.purple_700)

        // Asserts

        assertEquals(expectedMale, resultMale)
        assertEquals(expectedFemale, resultFemale)
        assertEquals(expectedElse, resultElse)

    }

    }
