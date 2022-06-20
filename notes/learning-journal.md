heading: Learning Journal
date: 2020-09-22
id: learning-journal

# Learning Journal

## Org-mode table UI and autoformatting is really well thought out and nice

## ~backward-kill-\*

-   `backward-kill-sentence`: C-x DEL

## Doom Emacs cmd &rsquo;&rsquo;: switch between buffers

## `position: sticky`

<https://medium.com/@elad/css-position-sticky-how-it-really-works-54cd01dc2d46>
<https://www.digitalocean.com/community/tutorials/css-position-sticky>
Requirements

-   Sticky element
-   Sticky container with other content

## Browser zoom affects font-size and relative font units

## anki-connect error addon

\#+end<sub>comment</sub>

## Sign multiple commits     :git:

`git rebase --exec 'git commit --amend --no-edit -n -S' -i <sha>`

## Toggle the literal or descriptive display of links in Org-mode     :emacs:

<span class="timestamp-wrapper"><span class="timestamp">[2020-10-15 Thu]</span></span>

You can toggle Org-mode links to display the description or the literal syntax wiht `org-toggle-link-display`. This function is bound to `M-SPC m d T` in Doom Emacs

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

## Nested shorthand anonymous functions are not allowed     :clojure:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-12 Mon]</span></span>*

Nested `#()` are not allowed. I discovered this while solving 4Clojure problem 40, [Interpose a seq](/4clojure).

**Legal**:

    ((fn
      [sep coll]
      (drop-last (reduce #(conj %1 %2 sep) [] coll))) "and" [1 2 3])
    
    ;; =>  (1 "and" 2 "and" 3)

**Illegal**:

    (#(drop-last (reduce #(conj %3 %4 %1) [] %2)) "and" [1 2 3])
    
    ;; Caused by clojure.lang.LispReader$ReaderException
    ;;   java.lang.IllegalStateException: Nested #()s are not allowed

## Modify separate keyboards independently on macOS     :macos:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-04 Sun]</span></span>*

For example, if you have mapped <kbd>caps lock</kbd> to the escape key on a Macbook Pro keyboard, when you plug in an external Apple keyboard, you have to modify its <kbd>caps lock</kbd> key independently.

## Responsive widths and heights     :css:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-09-27 Sun]</span></span>*

Create a responsive element with a maximum width.

    .sidebar {
      width: 100%;
      max-width: 25rem;
    }

Note: A `width` must be defined for the `max-width` to take effect.

## Use the prefers-color-scheme media query to detect light or dark mode     :css:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-09-25 Fri]</span></span>*

    @media (prefers-color-scheme: dark) {
      :root {
        --color-primary: white;
        --color-background: black;
      }
    }

Reference: [prefers-color-scheme](https://developer.mozilla.org/en-US/docs/Web/CSS/@media/prefers-color-scheme)

## Reset an initial Git commit     :git:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-09-24 Thu]</span></span>*

`git update-ref -d HEAD`

## Force Org-publish with prefix argument     :emacs:

*<span class="timestamp-wrapper"><span class="timestamp">[2020-09-24 Thu]</span></span>*

Force publish all files with Org-publish with prefix argument.

**Example**: `C-c org-publish`

> Org uses timestamps to track when a file has changed. The above functions normally only publish changed files. You can override this and force publishing of all files by giving a prefix argument to any of the commands above.

**Reference**: [Publishing (Org Mode Compact Guide)](https://orgmode.org/guide/Publishing.html)

## consult text search within file

`#find-term -- -g *.clj`

## consult-ripgrep in directory     :notes:

Regex search in directory

1.  prefix argument `SPC u` (`C-u`)
2.  `consult-ripgrep` (`consult-grep`)

