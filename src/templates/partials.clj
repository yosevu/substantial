(ns templates.partials
  (:require [hiccup.page :refer [html5]]
            [hiccup.element :refer [link-to]]))

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
   [:link {:href "css/a11y-dark.min.css" :rel "stylesheet"}]])

(defn backlinks
  [blinks]
  [:aside
   [:h4 "Links to this note"]
;;    [:ul (for [link links] [:li link])]])
   [:ul (map (fn [[resource text]]
               [:li
                (link-to (str site-url "/" resource) text)]) blinks)]])

(defn body
  [content blinks]
  [:body
   [:div.container
    [:nav [:a {:href (str site-url "/index.html")} "Index"]]
    [:main
     [:article content]
     (when (seq blinks) (backlinks blinks))]
    [:script {:src "js/highlight.min.js"}]
    [:script "hljs.highlightAll()"]]])

(defn page [head body]
  (html5 {:lang "en"}
         head
         body))

(comment 
  )
