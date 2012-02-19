import app.Role
import app.Message
import app.User
import org.apache.shiro.crypto.hash.Sha1Hash

class BootStrap {

    def init = { servletContext ->
    	def user = new Role(name: 'User').save()
        def admin = new Role(name: 'Administrator').save()
        new User(username:'mjones',title:'Miss',firstName:'Martha',lastName:'Jones',password: new Sha1Hash("admin").toHex(), role:admin).save()
        new User(username:'flancelot',title:'Mr',firstName:'Fred',lastName:'Lancelot',password: new Sha1Hash("password").toHex(), role:user).save()
        new Message( title:'The Knights Who Say Nee', detail:'They are after a shrubbery.' ).save()
        new Message( title:'The Black Knight', detail:"Just a flesh wound." ).save()
        new Message( title:'air speed velocity of a swallow', detail:"African or European?" ).save()
        
    }
    def destroy = {
    }
}
