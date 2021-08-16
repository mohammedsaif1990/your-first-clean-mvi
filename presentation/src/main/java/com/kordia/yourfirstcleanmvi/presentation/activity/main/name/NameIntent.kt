package com.kordia.yourfirstcleanmvi.presentation.activity.main.name

import com.kordia.yourfirstcleanmvi.presentation.utils.appstructure.BaseIntent

/**
 * NameIntent class to specify our events intentions.
 *
 * @author Mohammedsaif Kordia
 */
sealed class NameIntent : BaseIntent() {

    data class InsertName(val name: String) : NameIntent()

    object DeleteAll : NameIntent()

    object GetAll : NameIntent()
}
