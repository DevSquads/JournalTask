Rails.application.routes.draw do
  root 'articles#index'
  devise_for :users

  resources :articles do
    member do
      put "approve", to: "articles#approve"
    end
  end


  resources :users do
  end
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
end
