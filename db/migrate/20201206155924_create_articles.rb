class CreateArticles < ActiveRecord::Migration[5.2]
  def change
    create_table :articles do |t|
      t.belongs_to :user
      t.string :title
      t.string :description
      t.string :author_name
      t.timestamps
    end
  end
end
