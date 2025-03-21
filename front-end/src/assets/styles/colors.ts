export type ColorName =
  | 'gold'
  | 'gold-dark'
  | 'gold-light'
  | 'real-white'
  | 'neutral-white'
  | 'neutral-black'
  | 'neutral-dark200'
  | 'neutral-dark100'
  | 'neutral-gray'
  | 'neutral-light100'
  | 'neutral-light200'
  | 'neutral-light300'
  | 'coral-red'
  | 'avocado'
  | 'pistachio'

export const colorMap: Record<ColorName, string> = {
  'gold': '#F5C400',
  'gold-dark': '#E2A600',
  'gold-light': '#FFDA45',
  'real-white': '#FFFFFF', 
  'neutral-white': '#FEFDFC',
  'neutral-black': '#0B0B0C',
  'neutral-dark200': '#535257',
  'neutral-dark100': '#6C6C6D',
  'neutral-gray': '#B4B3BA',
  'neutral-light100': '#ECEBED',
  'neutral-light200': '#F3F2F2',
  'neutral-light300': '#FCFCFE',
  'coral-red': '#FF5E5E',
  'avocado': '#568203',
  'pistachio': '#84B067'
};