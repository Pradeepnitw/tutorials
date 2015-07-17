" How to install:
"
" rm -rf ~/.vim ~/.vimrc
" mkdir -p ~/.vim/bundle
" cd ~/.vim/bundle
" git clone git@github.com:gmarik/vundle.git
"
" Then open vim, ignore the error messages, and run: :BundleInstall
" 
" Restart vim and enjoy!
"
" Note: you need flake8 (pip install flake8) installed to actually enjoy this!

set nocp
set number
set backspace=indent,eol,start
set showmatch
set hlsearch
set softtabstop=4
set sw=4
set expandtab
set ruler
set showmode
set ul=200
set cindent
set autoindent
set nobackup
set history=50
"set wildmenu
set ttyfast
set incsearch
set cursorline
"set nowrap
set linebreak
set ttymouse=xterm2
set laststatus=2 " Always show the statusline
set encoding=utf-8

if has("mouse")
    set mouse=a
endif

if has("gui")
    set guicursor+=a:blinkon0
    set tb=tooltips
    set guioptions-=m " remove menu bar
    set guioptions-=T " remove toolbar
    set guioptions-=r " remove right-hand scroll bar
    set guioptions-=L " remove left-hand scroll bar
    set guioptions+=c
    "set guifont=Inconsolata:h13
endif

if has("syntax")
    syntax on
endif

au BufRead,BufNewFile *.py set ts=4 sts=4 sw=4
au BufRead,BufNewFile *.blt,*.rtx set ft=c
au BufRead,BufNewFile *.rb set ts=2 sts=2 sw=2
au BufRead,BufNewFile *.go set ts=8 sts=8 sw=8 expandtab ft=go
au BufRead,BufNewFile *.coffee set ts=2 sts=2 sw=2 ft=coffee
au BufRead,BufNewFile *.jade set ts=2 sts=2 sw=2 ft=jade
au BufRead,BufNewFile *.yaml set ts=2 sts=2 sw=2

""" Mappings

map Q gq
nnoremap <silent> <C-j> :tabnext<CR>
nnoremap <silent> <C-k> :tabprevious<CR>

""" Vundle

set filetype=off
set rtp+=~/.vim/bundle/vundle/
call vundle#rc()
filetype plugin indent on

Bundle 'Jinja'
Bundle 'desert256.vim'

Bundle 'gmarik/vundle'
Bundle 'Lokaltog/vim-powerline'
Bundle 'scrooloose/syntastic'
Bundle 'kien/ctrlp.vim'
Bundle 'davidhalter/jedi-vim'
Bundle 'puppetlabs/puppet-syntax-vim'
Bundle 'jnwhiteh/vim-golang'
Bundle 'kchmck/vim-coffee-script'
Bundle 'digitaltoad/vim-jade'

""" Scheme

set t_Co=256
colorscheme zenburn

""" Powerline

let g:Powerline_symbols = 'unicode'

""" Syntastic

" We use filetype=c for rathaxes files, obviously we don't want to run
" syntastic on them.
let g:syntastic_mode_map = { 'mode': 'active',
                           \ 'active_filetypes': ['python'],
                           \ 'passive_filetypes': ['c'] }

let g:syntastic_c_compiler_options = "-std=c99 -D_GNU_SOURCE=1 -D_POSIX_C_SOURCE=200809L -I./compat/"

""" Omni-Completion

set ofu=syntaxcomplete#Complete
set completeopt=menu

""" jedi-vim

let g:jedi#show_call_signatures = "0"

""" command-p

let g:ctrlp_custom_ignore = '.*\.py[co]$'
