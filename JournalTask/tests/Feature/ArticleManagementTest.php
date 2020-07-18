<?php

namespace Tests\Feature;

use App\User;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

class ArticleManagementTest extends TestCase
{
    use RefreshDatabase;

    /** @test */
    public function an_article_can_be_created()
    {
        $this->withoutExceptionHandling();

        $user = factory(User::class)->create();
        $this->actingAs($user)
            ->post('/articles/create', [
                'author_id' => $user->id,
                'title' => 'Article One',
                'description' => 'Description for Article'
            ]);

        $this->assertCount(1, Article::all());
    }
}
