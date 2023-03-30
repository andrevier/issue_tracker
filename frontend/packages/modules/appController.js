import Issue from './model/issue.js';
import Project from './model/project.js';
import User from './model/user.js';
import fakeUser from './fakeUser.js';

class AppController {
    constructor() {
        this.user = fakeUser;
    }

    userLogin(email, password) {
        // Login logic.
        if (this.user.email === email && this.user.password === password) {

            window.localStorage.setItem('user', this.user);
            
            window.location.href= './projects.html';
        } else {
            console.log('wrong user name or email.');
        }
    }

    userLogout() {
        //Logout logic
        window.location.href='./login.html'
    }

    userSignup(name, email, password) {
        // Signup logic.
        this.user = {
            id: 99,
            name: name,
            email: email,
            password: password,
            projects: []
        }

        window.localStorage.setItem('user', this.user);

        window.location.href='./projects.html';
    }

    getUserProjects() {
        return this.user.getProjects();
    }
}

export default AppController;