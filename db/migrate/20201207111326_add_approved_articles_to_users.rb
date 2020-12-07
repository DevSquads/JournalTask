class AddApprovedArticlesToUsers < ActiveRecord::Migration[5.2]
  def change
    add_column :users, :approved_articles, :integer , default: 0
  end
end
