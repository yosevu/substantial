(ns substantial.backlinks
  "Transform Markdown backlinks to HTML strings.
   
   `add-backlinks`"
  (:require
   [clojure.string :as string]
   [substantial.metadata :refer [get-meta-dictionary]]))

(def site-url "https://notes.yosevu.com")

(def backlink-match
  "Matches a relative backlink.
   
   Example:
   
   before text [I am a backlink](/i-am-a-backlink) after text
   
   $1: (.*[)          : before text [
   $2: (.+)           : I am a backlink
   $3: (]()           : ](
   $4: (/)            : /
   $5: ([a-z0-9|-]+)  : i-am-a-backlink
   $6: (.*)           : ) after text"
  #"(.*\[)(.+)(\]\()(\/)([a-z0-9|-]+)(.*)")

(defn- replace-backlink
  "Prepends: site-url to relative backlink.
   
   Example:

   [I am a backlink](/i-am-a-backlink)
   [Backlink](https://<site-url>/i-am-a-backlink.html)"
  [site-url]
  ;; (str "$1" "$2" "$3" site-url "$4" "$5" ".html" "$6"))
  (str "$1"      ; Preceding text up start of markdown link text ([) 
       "$2"      ; Link text
       "$3"      ; End of markdown link text (])
       site-url  ; Site url
       "$4"      ; Preceding forward-slash (/) for relative link
       "$5"      ; Relative link path
       ".html"   ; File extension
       "$6"))    ; End of url ()) and following text

(defn transform-backlink
  "(transform-backlink string)
   Normalize markdown backlink."
  [backlink]
  (string/replace backlink backlink-match
                  (replace-backlink site-url)))

(defn get-backlinks
  "(get-backlinks string)
   Match backlinks in markdown filestring."
  [filestring]
  (re-seq (re-pattern (str "\\[.*?\\]\\(/[a-z0-9|-]+\\)"))
          filestring))

(defn- get-backlink-slug
  "(get-backlink-slug string)"
  [backlink]
  (re-find #"(?<=\/).+?(?=\))" backlink))

(defn- get-backlink-heading
  [slug]
  (:heading ((keyword slug) (get-meta-dictionary))))

(defn get-backlink-heading-and-slug
  [backlink]
  (let [slug (get-backlink-slug backlink)
        heading (get-backlink-heading slug)]
    [slug heading]))

(defn collect-backlinks
  "(collect-backlinks string)
   Collect backlinks for note."
  [filestring]
  (vec (set (map get-backlink-heading-and-slug
                      (get-backlinks filestring)))))

(defn add-backlinks
  [text state]
  (let [line-with-backlinks (transform-backlink text)] 
    [line-with-backlinks state]))

(comment
  ;; Base lines to take on config.edn lines 
  )