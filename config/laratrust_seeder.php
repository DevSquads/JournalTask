<?php

return [
    /**
     * Control if the seeder should create a user per role while seeding the data.
     */
    'create_users' => false,

    /**
     * Control if all the laratrust tables should be truncated before running the seeder.
     */
    'truncate_tables' => true,

    'roles_structure' => [
        'super_admin' => [
            'admins' => 'c,r,u,d',
            'users' => 'c,r,u,d',
            'articles' => 'c,r,u,d,p,j',
            'roles' => 'c,r,u,d',
        ],
        'user' => [
            'articles' => 'c,r,u',
        ],
    ],

    'permissions_map' => [
        'c' => 'create',
        'r' => 'read',
        'u' => 'update',
        'd' => 'delete',
        'p' => 'publish',
        'j' => 'reject'
    ]
];
