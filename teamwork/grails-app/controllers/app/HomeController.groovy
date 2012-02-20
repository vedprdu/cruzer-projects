package app

class HomeController {

    def index = { 
        def messages = Message.list( sort: 'lastUpdated', order: 'desc', fetch: [user:'eager'])
        def files = File.list( sort:'lastUpdated', order:'desc', fetch:[user:'eager'] )
        return [messages: messages, files: files]
    }
}
