# **substantial**

A static site generator of substance. A space for thoughts to be whole, connected, and real; not imaginary; to actually exist.

> How can I be substantial if I do not cast a shadow? I must have a dark side also If I am to be whole.
>
> &mdash; Carl Jung

### Features
- Create a new site.
- Build a site from Markdown files.
- Customize site with `config.edn`.
- Generate an index page with links to all pages.
- Connect pages with [backlinks](#backlinks).
- Collect backlinks for each page.
- Publish to GitHub Pages

### Example Sites

- https://yosevu.github.io/substantial
- https://notes.yosevu.com

## Usage

### Create

Create a site from the substantial template.

``` sh
clj -Sdeps '{:deps {io.github.yosevu/substantial {:git/tag "TAG" :git/sha "SHORT_SHA"}}}' -Tnew create :template org.substantial/new :name myname/mysite
```

### Build

Build the site.

``` sh
clj -X:build
```

### Publish

Publish the site.

- Create a [Github Pages](https://pages.github.com/) site to publish
- Add a `CNAME` to the `public` directory.
- Publish with Clojure
```
clj -X:publish
```
Expected output:
``` sh
clj -X:publish
Building site.
Built 10 pages.
Done.

Publishing site.
To github.com:username/my-site.git
   782f5cd..383c707  383c707a75c849d2fadb550718323e436ab15e49 -> gh-pages
```

- Publish with Git:
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
- `date` (required): The publication date.
- `id` (required): The unique short name (slug) displayed in the url used to link to the note.

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

## Development 

### Design Goals

- Simplicity
- Flexibility

### Technical Goals
- [x] Create site from template.
- [x] Build site form Markdown files. 
- [x] Publish site.
- [x] Build for developers or anyone comfortable following technical instructions and using the command line.
- [x] Syntax highlighting.
- [x] Use [Hiccup](https://github.com/weavejester/hiccup) for HTML templating.
- [ ] Customize theme.
- [ ] Customize template with Hiccup.
- [ ] Create component driven development environment with Storybook.

### Project Structure

```
.
├── README.md
├── deps.edn
├── resources
│   └── org
│       └── substantial
│           └── new
│               ├── content
│               │   ├── another-note.md
│               │   ├── index.md
│               │   └── my-note.md
│               ├── root
│               │   ├── config.edn
│               │   └── deps.edn
│               ├── static
│               │   ├── css
│               │   │   ├── highlight.css
│               │   │   └── main.css
│               │   └── js
│               │       └── highlight.js
│               └── template.edn
└── src
    └── substantial
        ├── backlinks.clj
        ├── core.clj
        ├── metadata.clj
        ├── notes.clj
        ├── pages.clj
        ├── partials.clj
        ├── scripts.clj
        └── utilities.clj
 ```

### [Calva](https://calva.io/) (VS Code)

- `Calva: Start a Project REPL and Connect (aka Jack-In)`
- `Calva: Load/Evaluate Current File and its Requires/Dependencies`

### [CIDER](https://cider.mx/) (Emacs)

- `cider-jack-in (cider-jack-in-clj`
- `cider-ns-refresh`
