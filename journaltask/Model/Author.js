import firebase from "../firebase";
import userRole from "../Model/UserRole";
let authRef;

class Author {
  name;
  email;

  constructor() {
    if (process.browser) {
      let frbase = new firebase();
      authRef = frbase.auth;
    }
  }

  async register(email, password, username) {
    authRef
      .createUserWithEmailAndPassword(email, password)
      .then(function (result) {
        userRole.addRole(0,result.user.uid);
        return result.user.updateProfile({
          displayName: username,
        });
        
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  async registerAdmin(email, password, username) {
    authRef
      .createUserWithEmailAndPassword(email, password)
      .then(function (result) {
        userRole.addRole(1,result.user.uid);
        return result.user.updateProfile({
          displayName: username,
        });
        
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  async login(email, password) {
    return authRef
      .signInWithEmailAndPassword(email, password)
      .then((response) => {
        console.log("USER ID ", response.user.uid);
      });
  }

  isAuthenticated() {
    authRef.onAuthStateChanged(async (user) => {
      try {
        if (user) {
          console.log("user from authentication ", user);
          return user;
        }
      } catch (err) {
        console.log("something went wrong ", err);
      }
    });
  }


  logout(){
      return authRef.signOut().then(()=>{
        window.location.href = "/Login";
      });
      
  }
}

let author = new Author();
export default author;
