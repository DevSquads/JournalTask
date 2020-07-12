import { firestoreReducer } from "react-redux-firebase";

export const createProject = (project) => {
    return (dispatch, getState, getFirebase) => {
        const profile = getState().firebase.profile;
        const authorId = 25252; //getState().firebase.auth.uid;
        const authorFirstName = "aaa" ;//profile.firstName;
        const authorLastName = "sss" ;//profile.lastName;
        const firestore = getFirebase().firestore();
        const data = {
            ...project,
            authorFirstName: authorFirstName,
            authorLastName: authorLastName,
            authorId: authorId,
            createdAt: new Date()
        }
        firestore.collection('projects').doc().set(data).then(() => {
            dispatch({
                type: 'CREATE_PROJECT',
                project: project
            });
        }).catch((err) => {
            dispatch({
                type: 'CREATE_PROJECT_ERROR',
                err
            });
        });
    }
};