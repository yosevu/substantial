(ns templates.partials
  (:require [hiccup.page :refer [html5]]))

(def site-url "https://notes.yosevu.com")

(defn head [{:keys [title desc]}]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:http-equiv "X-UA-Compatible"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0"}]
   [:meta {:name "author"
           :content "Yosevu Kilonzo"}]
   [:meta {:name "description"
           :content desc}]
   [:meta {:name "keywords"
           :content ""}]
   [:title title]
   [:link {:href "css/main.css"      :rel "stylesheet"}]
   [:link {:href "css/a11y-dark.css" :rel "stylesheet"}]])

(defn body [content]
  [:body
   [:nav [:a {:href (str site-url "/index.html")} "Home"]]
   [:main
    [:article content]]])

(defn page [head body]
  (html5 {:lang "en"}
         head
         body))

(comment 
  (page (head {:title "hello" :desc "world"}) (body "content"))
  (defn temp [{:keys [title description]}]
    [:div
     [:title title]
     [:p description]])

  (temp {:title "hello" :description "world"})
  )
