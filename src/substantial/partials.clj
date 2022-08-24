(ns substantial.partials
  (:require [hiccup.page :refer [html5]]
            [hiccup.element :refer [link-to]]))

(defn create-head [{:keys [title author desc]}]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:http-equiv "X-UA-Compatible"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0"}]
   [:meta {:name "author"
           :content author}]
   [:meta {:name "description"
           :content desc}]
   [:meta {:name "keywords"
           :content ""}]
   [:title title]
   [:link {:href "css/main.css"      :rel "stylesheet"}]
   [:link {:href "css/highlight.css" :rel "stylesheet"}]])

(defn create-backlinks
  [site-url blinks]
  [:aside
   [:h4 "Links to this note"]
   ;; [:ul (for [link links] [:li link])]])
   [:ul {:class "backlinks"}
    (map (fn [[resource text]]
           [:li
            (link-to (str site-url "/" resource) text)]) blinks)]])

(defn create-date
  [date] [:time date])

(defn create-body
  [{:keys [site-url]} [content blinks]]
  [:body
   [:div.container
    [:nav [:a {:href (str site-url "/")} "Index"]]
    [:main
     [:article content]
     (when (seq blinks) (create-backlinks site-url blinks))]
    [:script {:src "js/highlight.js"}]
    [:script "hljs.highlightAll()"]]])

(defn create-page [head body]
  (html5 {:lang "en"}
         head
         body))

(comment)
