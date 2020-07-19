import firebase from "../firebase";

let roleRef, role;
 class UserRole {
  static roleInstance;
  constructor() {
    role = ["author", "admin"];
    if (process.browser) {
      let fbase = new firebase();
      roleRef = fbase.app.firestore().collection("roles");
    }
    if (!UserRole.roleInstance) {
      UserRole.roleInstance = this;
      return UserRole.roleInstance;
    } else {
      return UserRole.roleInstance;
    }
  }

  async addRole(rolenum, uid) {
    roleRef
      .add({
        userId: uid,
        role: role[rolenum],
      })
      .then(function (ref) {
        console.log("role ref added.", ref);
      })
      .catch(function (error) {
        console.error("Error adding role: ", error);
      });
  }
  async getUserRole(id) {
    let storeValue;
    let firstStep = await roleRef.where("userId", "==", id).get();
    let secondStep = await firstStep.forEach((doc) => {
      storeValue = doc.data().role;
    });
    console.log("user stored role", storeValue);
    return storeValue;
  }


}

let userRoleInstance = new UserRole();
export default userRoleInstance;