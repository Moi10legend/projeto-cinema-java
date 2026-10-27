interface GerenciaDeFilmes {
    public void incluirFilme(Filme filme);
    public void excluirFilme(String nomeFilme);
    public void alterarFilme(String nomeFilmeBusca, Filme filmeAtualizado);
}
