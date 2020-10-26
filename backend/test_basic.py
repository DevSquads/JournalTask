import os
import unittest
import json

from flaskr import app, db, Author, Article, database_uri


class basic_tests(unittest.TestCase):

    def setUp(self):
        app.config['TESTING'] = True
        app.config['WTF_CSRF_ENABLED'] = False
        app.config['DEBUG'] = False
        app.config['SQLALCHEMY_DATABASE_URI'] = database_uri

        self.app = app.test_client()


        self.new_author = {
            'mail': 'author@news.com',
            'name': 'author'
        }

        self.author = {
            'mail': 'ahmed@gmail.com',
            'name': 'Ahmed'
        }

    def tearDown(self):
        pass


    def test_index_success(self):
        res = self.app.get('/')
        data = json.loads(res.data)

        self.assertEqual(res.status_code, 200)
        self.assertEqual(data['message'], "Hello Ahmed")


    def test_get_all_authors_success(self):
        res = self.app.get('/authors')
        data = json.loads(res.data)

        self.assertEqual(res.status_code, 200)
        self.assertEqual(data['success'], True)

        self.assertTrue(data['response'])

    
    def test_post_update_del_author_success(self):
        post_response = self.app.post(
            '/authors', 
            json=self.new_author
        )

        data = json.loads(post_response.data)

        self.assertEqual(post_response.status_code, 200)
        self.assertEqual(data['success'], True)
        
        self.assertTrue(data['response'])

        id = data['response']['id']

        update_res = self.app.patch(
            '/authors/' + str(id), 
            json={
                'name': 'test'
            }
        )

        _data = json.loads(update_res.data)

        self.assertEqual(update_res.status_code, 200)
        self.assertEqual(_data['success'], True)
        
        self.assertTrue(_data['response'])


        del_response = self.app.delete('/authors/' + str(id))

        data_ = json.loads(del_response.data)

        self.assertEqual(del_response.status_code, 200)
        self.assertEqual(data_['success'], True)

        

    def test_post_old_author_success(self):
        res = self.app.post(
            '/authors', 
            json=self.author
        )

        data = json.loads(res.data)

        self.assertEqual(res.status_code, 200)
        self.assertEqual(data['success'], True)
        
        self.assertTrue(data['response'])

    def test_post_author_fail_(self):
        res = self.app.post(
            '/authors', 
            json={
                'name': 'author'
            }
        )

        data = json.loads(res.data)

        self.assertEqual(res.status_code, 422)
        self.assertEqual(data['success'], False)
        self.assertEqual(data['message'], 'unprocessable')

    def test_post_author_fail(self):
        res = self.app.post(
            '/authors', 
            json={
                'mail': 'author@news.com'
            }
        )

        data = json.loads(res.data)

        self.assertEqual(res.status_code, 400)
        self.assertEqual(data['success'], False)
        self.assertEqual(data['message'], 'bad request')
        
    def test_get_author_success(self):
        res = self.app.get('/authors/1')
        data = json.loads(res.data)

        self.assertEqual(res.status_code, 200)
        self.assertEqual(data['success'], True)

        self.assertTrue(data['response'])


    def test_get_author_fail(self):
        res = self.app.get('/authors/100')
        data = json.loads(res.data)

        self.assertEqual(res.status_code, 404)
        self.assertEqual(data['success'], False)
        self.assertEqual(data['message'], 'resource not found')


    def test_update_author_fail(self):
        res = self.app.patch(
            '/authors/100', 
            json={}
        )

        data = json.loads(res.data)

        self.assertEqual(res.status_code, 400)
        self.assertEqual(data['success'], False)
        self.assertEqual(data['message'], 'bad request')

    def test_update_author_fail_(self):
        res = self.app.patch(
            '/authors/100', 
            json={
                'name': 'author'
            }
        )

        data = json.loads(res.data)

        self.assertEqual(res.status_code, 404)
        self.assertEqual(data['success'], False)
        self.assertEqual(data['message'], 'resource not found')


    def test_delete_author_fail(self):
        res = self.app.delete('/authors/100')

        data = json.loads(res.data)

        self.assertEqual(res.status_code, 404)
        self.assertEqual(data['success'], False)
        self.assertEqual(data['message'], 'resource not found')


if __name__ == "__main__":
    unittest.main()