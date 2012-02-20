package app

import grails.test.*

class UserTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCanDetermineIfAPasswordNeedsHashing() {
        def user = new User( password: 'oldpassword' )
        assertTrue( user.needToHash( 'newpassword' ) )
        assertFalse( user.needToHash( 'oldpassword' ) )
    }

}
