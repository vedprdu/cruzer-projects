import app.Role
import app.Message
import app.User
import org.apache.shiro.crypto.hash.Sha1Hash
import org.apache.shiro.crypto.hash.Sha256Hash

class BootStrap {

    def init = { servletContext ->
    	def user = new Role(name: 'User').save()
        def admin = new Role(name: 'Administrator').save()
        def mrogers = new User(username:'mrogers',title:'Mr',firstName:'Maurice',lastName:'Rogers',password: new Sha256Hash("Debbiedo1").toHex(), role:admin).save()
        def mjones = new User(username:'mjones',title:'Miss',firstName:'Martha',lastName:'Jones',password: new Sha256Hash("admin").toHex(), role:admin).save()
        def flancelot = new User(username:'flancelot',title:'Mr',firstName:'Fred',lastName:'Lancelot',password: new Sha256Hash("password").toHex(), role:user).save()
        new Message( title:'The Knights Who Say Nee', detail:'They are after a shrubbery.',user:mjones).save()
        new Message( title:'The Black Knight', detail:"Just a flesh wound." ,user: flancelot).save()
        new Message( title:'air speed velocity of a swallow', detail:"African or European?",user: flancelot ).save()
        
    }
    def destroy = {
    }
}
