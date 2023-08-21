package io.smooch.demoapp

import android.app.Application
import io.smooch.core.CardSummary
import io.smooch.core.Conversation
import io.smooch.core.ConversationDelegate
import io.smooch.core.ConversationDetails
import io.smooch.core.ConversationEvent
import io.smooch.core.InitializationStatus
import io.smooch.core.Logger
import io.smooch.core.LoginResult
import io.smooch.core.LogoutResult
import io.smooch.core.Message
import io.smooch.core.MessageAction
import io.smooch.core.MessageModifierDelegate
import io.smooch.core.MessageUploadStatus
import io.smooch.core.PaymentStatus
import io.smooch.core.Settings
import io.smooch.core.Smooch
import io.smooch.core.SmoochConnectionStatus

class DemoApplication : Application(), ConversationDelegate, MessageModifierDelegate {

    companion object {
        private const val LOG_TAG = "DemoApplication"
    }

    override fun onCreate() {
        super.onCreate()

        // Below is where you would put your app's android-sdk integrationId to initialize the Smooch class;
        // Find it on the Sunshine Conversations dashboard, or via API: https://docs.smooch.io/rest/#get-sdk-ids
        Smooch.init(this, Settings("59a93c829200175800018222")) { response ->
            Logger.i(LOG_TAG, "Init complete")
            Logger.i(LOG_TAG, "response.status: " + response.status)
            Logger.i(LOG_TAG, "response.error: " + response.error)
            Logger.i(LOG_TAG, "response.data: " + response.data)
        }
        Smooch.setConversationDelegate(this)
        Smooch.setMessageModifierDelegate(this)
    }

    //region ConversationDelegate
    override fun onMessagesReceived(conversation: Conversation, messages: List<Message>) {
        Logger.i(LOG_TAG, "onMessagesReceived")
    }

    override fun onMessagesReset(conversation: Conversation, messages: List<Message>) {
        Logger.i(LOG_TAG, "onMessagesReset")
    }

    override fun onUnreadCountChanged(conversation: Conversation, unreadCount: Int) {
        Logger.i(LOG_TAG, "Unread count changed. New count: $unreadCount")
    }

    override fun onMessageSent(message: Message, status: MessageUploadStatus) {
        Logger.i(LOG_TAG, "onMessagesSent")
    }

    override fun onConversationEventReceived(conversationEvent: ConversationEvent) {
        Logger.i(LOG_TAG, "onConversationEventReceived")
        Logger.i(LOG_TAG, "type " + conversationEvent.type)
        Logger.i(LOG_TAG, "name " + conversationEvent.name)
        Logger.i(LOG_TAG, "avatarUrl " + conversationEvent.avatarUrl)
        Logger.i(LOG_TAG, "role " + conversationEvent.role)
    }

    override fun onInitializationStatusChanged(status: InitializationStatus) {
        Logger.i(LOG_TAG, "New initialization status: " + status.name)
    }

    override fun onLoginComplete(result: LoginResult) {
        Logger.i(LOG_TAG, "Login result: " + result.name)
    }

    override fun onLogoutComplete(result: LogoutResult) {
        Logger.i(LOG_TAG, "Logout result: " + result.name)
    }

    override fun onPaymentProcessed(messageAction: MessageAction, status: PaymentStatus) {
        Logger.i(LOG_TAG, "onPaymentProcessed")
    }

    override fun shouldTriggerAction(messageAction: MessageAction): Boolean {
        Logger.i(LOG_TAG, "Return false here to block message actions!")
        return true
    }

    override fun onCardSummaryLoaded(cardSummary: CardSummary) {
        Logger.i(LOG_TAG, "onCardSummaryLoaded")
    }

    override fun onSmoochConnectionStatusChanged(status: SmoochConnectionStatus) {
        Logger.i(LOG_TAG, "onSmoochConnectionStatusChanged: " + status.name)
    }

    override fun onSmoochShown() {
        Logger.i(LOG_TAG, "Smooch shown")
    }

    override fun onSmoochHidden() {
        Logger.i(LOG_TAG, "Smooch hidden")
    }

    override fun onConversationsListUpdated(conversationsList: List<Conversation>) {
        Logger.i(LOG_TAG, "Conversations list updated")
    }
    //endregion

    //region MessageModifierDelegate
    override fun beforeSend(conversationDetails: ConversationDetails, message: Message): Message {
        Logger.i(LOG_TAG, "beforeSend")
        return message
    }

    override fun beforeDisplay(conversationDetails: ConversationDetails, message: Message): Message {
        Logger.i(LOG_TAG, "beforeDisplay")
        return message
    }

    override fun beforeNotification(conversationId: String, message: Message): Message {
        Logger.i(LOG_TAG, "beforeNotification")
        return message
    }
    //endregion

}