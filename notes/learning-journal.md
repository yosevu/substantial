heading: Learning journal
subheading:
date: 2020-09-22
id: learning-journal
tags:

# Learning Journal


<a id="orgdfae433"></a>

## Org-mode table UI and autoformatting is really well thought out and nice


<a id="orgba3badd"></a>

## ~backward-kill-\*

-   `backward-kill-sentence`: C-x DEL


<a id="org51b8182"></a>

## Doom Emacs cmd &rsquo;&rsquo;: switch between buffers


<a id="org110e24c"></a>

## `position: sticky`

<https://medium.com/@elad/css-position-sticky-how-it-really-works-54cd01dc2d46>
<https://www.digitalocean.com/community/tutorials/css-position-sticky>
Requirements

-   Sticky element
-   Sticky container with other content


<a id="org64754e1"></a>

## Browser zoom affects font-size and relative font units


<a id="orgd9438df"></a>

## anki-connect error addon

\#+end<sub>comment</sub>


<a id="orgd63d233"></a>

## Sign multiple commits     :git:

`git rebase --exec 'git commit --amend --no-edit -n -S' -i <sha>`


<a id="org76086a2"></a>

## Toggle the literal or descriptive display of links in Org-mode     :emacs:

<span class="timestamp-wrapper"><span class="timestamp">[2020-10-15 Thu]</span></span>

You can toggle Org-mode links to display the description or the literal syntax wiht `org-toggle-link-display`. This function is bound to `M-SPC m d T` in Doom Emacs


<a id="orge421a6c"></a>

## Unexpected EOF while reading item 17 of list     :clojure:

<span class="timestamp-wrapper"><span class="timestamp">[2020-10-14 Wed]</span></span>
This is a syntax compilation error due to a [list](https://clojuredocs.org/clojure.core/list) without a closing `)`.

    (ns firn.org
      "Functions for managing org-related things.
      Most of these functions are for operating on EDN-fied org-file
      Which are created by the rust binary."
      (:require [clojure.java.shell :as sh]
                [clojure.string :as s]
                [firn.util :as u]
                [cheshire.core :as json]
                ;; [firn.org :as org])
    
    ;; Unhandled clojure.lang.ExceptionInfo
    ;; Unexpected EOF while reading item 17 of list.
    ;; {:type :reader-exception, :ex-kind :eof}


<a id="org42b8a31"></a>

## Nested shorthand anonymous functions are not allowed     :clojure:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-12 Mon]</span></span>*

Nested `#()` are not allowed. I discovered this while solving 4Clojure problem 40, [Interpose a seq](4clojure-exercises.md).

**Legal**:

    ((fn
      [sep coll]
      (drop-last (reduce #(conj %1 %2 sep) [] coll))) "and" [1 2 3])
    
    ;; =>  (1 "and" 2 "and" 3)

**Illegal**:

    (#(drop-last (reduce #(conj %3 %4 %1) [] %2)) "and" [1 2 3])
    
    ;; Caused by clojure.lang.LispReader$ReaderException
    ;;   java.lang.IllegalStateException: Nested #()s are not allowed


<a id="org91ef443"></a>

## Modify separate keyboards independently on macOS     :macos:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-04 Sun]</span></span>*

For example, if you have mapped <kbd>caps lock</kbd> to the escape key on a Macbook Pro keyboard, when you plug in an external Apple keyboard, you have to modify its <kbd>caps lock</kbd> key independently.


<a id="org10a650f"></a>

## Responsive widths and heights     :css:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-09-27 Sun]</span></span>*

Create a responsive element with a maximum width.

    .sidebar {
      width: 100%;
      max-width: 25rem;
    }

Note: A `width` must be defined for the `max-width` to take effect.


<a id="org97a7b1c"></a>

## Use the prefers-color-scheme media query to detect light or dark mode     :css:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-09-25 Fri]</span></span>*

    @media (prefers-color-scheme: dark) {
      :root {
        --color-primary: white;
        --color-background: black;
      }
    }

Reference: [prefers-color-scheme](https://developer.mozilla.org/en-US/docs/Web/CSS/@media/prefers-color-scheme)


<a id="org9f8b77f"></a>

## Reset an initial Git commit     :git:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-09-24 Thu]</span></span>*

`git update-ref -d HEAD`


<a id="orgf32ca07"></a>

## Force Org-publish with prefix argument     :emacs:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-09-24 Thu]</span></span>*

Force publish all files with Org-publish with prefix argument.

**Example**: `C-c org-publish`

> Org uses timestamps to track when a file has changed. The above functions normally only publish changed files. You can override this and force publishing of all files by giving a prefix argument to any of the commands above.

**Reference**: [Publishing (Org Mode Compact Guide)](https://orgmode.org/guide/Publishing.html)

<a id="orga3c70f9"></a>

## consult text search within file

`#find-term -- -g *.clj`


<a id="orgeee3df7"></a>

## consult-ripgrep in directory     :notes:

Regex search in directory

1.  prefix argument `SPC u` (`C-u`)
2.  `consult-ripgrep` (`consult-grep`)

