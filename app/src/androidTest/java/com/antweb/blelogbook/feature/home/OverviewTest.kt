package com.antweb.blelogbook.feature.home

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.antweb.blelogbook.core.bluetooth.ConnectedAdapterServiceFake
import com.antweb.blelogbook.feature.home.overview.OverviewScreen
import com.antweb.blelogbook.feature.home.overview.OverviewViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.antweb.blelogbook.R
import com.antweb.blelogbook.core.bluetooth.DisconnectedAdapterServiceFake


@HiltAndroidTest
class OverviewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun `should show all information when adapter is enabled`() {
        val setTitleDummy: (String) -> Unit = {}
        val adapterService = ConnectedAdapterServiceFake()

        val viewModel = OverviewViewModel(adapterService)

        var statusLabel = ""
        var enabledText = ""
        var discoveringLabel = ""

        composeTestRule.setContent {
            statusLabel = stringResource(R.string.home_overview_adapter_status)
            enabledText = stringResource(R.string.home_overview_adapter_enabled)
            discoveringLabel = stringResource(R.string.home_overview_discovering)

            OverviewScreen(model = viewModel, setTitle = setTitleDummy)
        }

        composeTestRule.onNodeWithText(statusLabel).assertExists()
        composeTestRule.onNodeWithText(enabledText).assertExists()
        composeTestRule.onNodeWithText(adapterService.name).assertExists()

        composeTestRule.onNodeWithText(discoveringLabel).assertExists()
    }

    @Test
    fun `should show limited information when adapter is disabled`() {
        val setTitleDummy: (String) -> Unit = {}
        val adapterService = DisconnectedAdapterServiceFake()

        val viewModel = OverviewViewModel(adapterService)

        var statusLabel = ""
        var disabledText = ""
        var discoveringLabel = ""

        composeTestRule.setContent {
            statusLabel = stringResource(R.string.home_overview_adapter_status)
            disabledText = stringResource(R.string.home_overview_adapter_disabled)
            discoveringLabel = stringResource(R.string.home_overview_discovering)

            OverviewScreen(model = viewModel, setTitle = setTitleDummy)
        }

        composeTestRule.onNodeWithText(statusLabel).assertExists()
        composeTestRule.onNodeWithText(disabledText).assertExists()
        composeTestRule.onNodeWithText(adapterService.name).assertExists()

        composeTestRule.onNodeWithText(discoveringLabel).assertDoesNotExist()
    }
}