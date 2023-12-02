const handleDelete = id =>
    fetch('/laptimes/' + id, {method: 'DELETE'})
    .then(res => window.location.href = res.url)