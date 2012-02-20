package app

import grails.test.*

class MessageControllerTests extends GrailsUnitTestCase{

    def savedMessages
    def serviceControl
    
    protected void setUp() {
        super.setUp()
        savedMessages = []
        mockDomain(Message, savedMessages)
        mockController(MessageController)
        serviceControl = mockFor(UserService)
        serviceControl.demand.getAuthenticatedUser() {-> return new User() }
    }

    protected void tearDown() {
        super.tearDown()
        serviceControl.verify()

    }

    void testCreateViewIsRenderedIfTheMessageDoesNotValidate() {
        def messageController = messageController {
            params.title = 'no detail'
            params.detail = ''
        }
        messageController.save()
        assertEquals('create', messageController.renderArgs.view)
        def message = messageController.renderArgs.model.message
        assertEquals('no detail', message.title)
        assertEquals('', message.detail)
        assertNull(messageController.flash.toUser)
        assertEquals(0, savedMessages.size())
    }
    
    void testMessageCanBeCreated() {
        def messageController = messageController {
            params.title = 'detail'
            params.detail = 'some detail'
        }
        messageController.save()
        assertEquals('create', messageController.redirectArgs.action)
        assertEquals('Message [detail] has been added.',
            messageController.flash.toUser)
        assertEquals(1, savedMessages.size())
    }
    
    def messageController = { fn ->
        fn.delegate = new MessageController()
        fn.delegate.userService = serviceControl.createMock()
        fn()
        return fn.delegate
    }

}
