package app

import org.hibernate.FetchMode

class HomeController {

    def index = { 
        def messages = Message.list( sort: 'lastUpdated', order: 'desc', fetch: [user:'eager'])
        def files = File.withCriteria {
            currentVersion {
                order('dateCreated', 'desc')
                fetchMode( 'user', FetchMode.EAGER )
            }
            maxResults( 10 )
        }

        return [messages: messages, files: files]
    }
}
