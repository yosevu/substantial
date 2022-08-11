# substantial

> How can I be substantial if I do not cast a shadow? I must have a dark side also If I am to be whole.
>
> &mdash; Carl Jung

A minimal static site generator built with Clojure.

- Transforms Markdown files to HTML.
- Uses [Hiccup](https://github.com/weavejester/hiccup) for HTML templating.
- Aggregates backlinks.

### Example Sites

- https://yosevu.github.io/substantial
- https://notes.yosevu.com

## Usage

### Create site

``` sh
clj -Sdeps '{:deps {io.github.yosevu/substantial {:git/tag "v0.0.1" :git/sha "SHORT_SHA"}}}' -Tnew create :template org.substantial/new :name myname/mysite
```

### Build Site
``` sh
clj -X:build
```

### Publish Site

- Create a [Github Pages](https://pages.github.com/) site to publish
- Add a `CNAME` to the `public` directory.

Clojure:
```
clj -X:publish
```

Git:
```
git push origin `git subtree split --prefix public main`:gh-pages --force
```

### Site Config

Configure the site in `config.edn`.

Example:

```clojure
{:title       "My notes"
 :author      "My name"
 :description "These are my notes."
 :site-url    "https://notes.myname.com"}
```

### Metadata

- `heading` (required): The main heading.
- `id` (required): The unique short name (slug) displayed in the url used to link to the note.
- `date` (required): The publication date.

Example:
```yaml
heading: My Note
date: 2022-06-25
id: my-note
```

### Backlinks

A backlink is a reference to another page on the site. It connects thoughts and enriches them by building an emergent context around them.

> Often the context in which we are working suggests a multiplicity of links to other notes. This is especially the case when the card index is already voluminous. In such cases it is important to capture the connections radially, as it were, but at the same time also by right away recording back links in the slips that are being linked to. In this working procedure, the content that we take note of is usually also enriched.
>
> &mdash; Niklas Luhmann

Example:

```markdown
[I am a backlink](/i-am-a-backlink)
```

## Local Development 

### Project Structure

```
.
├── README.md
├── deps.edn
├── notes
│   ├── index.md
│   ├── note.md
├── public
│   ├── CNAME
│   ├── css
│   │   ├── a11y-dark.min.css
│   │   └── main.css
│   ├── note.html
│   ├── index.html
│   ├── js
│   │   └── highlight.min.js
├── src
│   ├── substantial
│   │   ├── backlinks.clj
│   │   ├── core.clj
│   │   ├── metadata.clj
│   │   ├── notes.clj
│   │   └── utilities.clj
│   └── templates
│       ├── note.clj
│       └── partials.clj
├── test
│   ├── backlink-test.md
│   └── substantial
│       └── core_test.clj
```

### [Calva](https://calva.io/) (VS Code)

- `Calva: Start a Project REPL and Connect (aka Jack-In)`
- `Calva: Load/Evaluate Current File and its Requires/Dependencies`

### [CIDER](https://cider.mx/) (Emacs)

- `cider-jack-in (cider-jack-in-clj`
- `cider-ns-refresh`

## Create binary

### Build uberjar

``` sh
clj -T:build uberjar
```
### Generate binary

``` sh
native-image -jar target/substantial-UBERJAR_VERSION.jar -H:Name=BINARY_NAME
```
