

# substantial

> How can I be substantial if I do not cast a shadow? I must have a dark side also If I am to be whole.
>
> &mdash; Carl Jung

A minimal static site generator built with Clojure.

- Transforms Markdown files to HTML.
- Uses [Hiccup](https://github.com/weavejester/hiccup) for HTML templating.
- Aggregates backlinks.

### Example Sites

- https://notes.yosevu.com/

## Usage

### Metadata

- `heading` (required): The main heading.
- `id` (required): The unique short name (slug) displayed in the url used to link to the note.
- `date` (required): The publication date.

Example:
```markdown
heading: This is Note 1
id: note-1
date: 2019-12-01
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
### Build Site

```
clj -M:build
```

### Publish Site

Create a [Github Pages](https://pages.github.com/) site to publish.

Clojure:
```
clj -X:publish
```

Git:
```
git push origin `git subtree split --prefix public main`:gh-pages --force
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

### [Calva](https://calva.io/)

- `Calva: Start a Project REPL and Connect (aka Jack-In)`
- `Calva: Load/Evaluate Current File and its Requires/Dependencies`