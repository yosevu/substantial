(ns templates.partials
  (:require [hiccup.page :refer [html5]]
            [hiccup.core :refer [html]]
            [hiccup.element :refer [link-to]]))

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
   [:title title]])

(defn body [content]
  [:body
   [:div {:id "substantial"} content]])

(defn page [head body]
  (html5 {:lang "en"}
         head
         body))


(comment
  (defn temp [{:keys [title description]}]
    [:div
     [:title title]
     [:p description]])

  (temp {:title "hello" :description "world"})
  )
